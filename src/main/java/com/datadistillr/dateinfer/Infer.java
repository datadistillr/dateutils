/*
 *  Copyright 2020 DataDistillR Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.datadistillr.dateinfer;

import com.datadistillr.dateinfer.elements.AMPM;
import com.datadistillr.dateinfer.elements.DateElement;
import com.datadistillr.dateinfer.elements.DayOfMonth;
import com.datadistillr.dateinfer.elements.Filler;
import com.datadistillr.dateinfer.elements.Hour12;
import com.datadistillr.dateinfer.elements.Hour24;
import com.datadistillr.dateinfer.elements.KeepOriginal;
import com.datadistillr.dateinfer.elements.Minute;
import com.datadistillr.dateinfer.elements.MonthNum;
import com.datadistillr.dateinfer.elements.MonthTextLong;
import com.datadistillr.dateinfer.elements.MonthTextShort;
import com.datadistillr.dateinfer.elements.NonDigit;
import com.datadistillr.dateinfer.elements.Second;
import com.datadistillr.dateinfer.elements.SingleDigit;
import com.datadistillr.dateinfer.elements.Timezone;
import com.datadistillr.dateinfer.elements.UTCOffset;
import com.datadistillr.dateinfer.elements.WeekdayLong;
import com.datadistillr.dateinfer.elements.WeekdayShort;
import com.datadistillr.dateinfer.elements.Year2;
import com.datadistillr.dateinfer.elements.Year4;
import com.datadistillr.dateinfer.rules.ActionClause;
import com.datadistillr.dateinfer.rules.And;
import com.datadistillr.dateinfer.rules.ConditionClause;
import com.datadistillr.dateinfer.rules.Contains;
import com.datadistillr.dateinfer.rules.Duplicate;
import com.datadistillr.dateinfer.rules.If;
import com.datadistillr.dateinfer.rules.Sequence;
import com.datadistillr.dateinfer.rules.Swap;
import com.datadistillr.dateinfer.rules.SwapDuplicateWhereSequenceNot;
import com.datadistillr.dateinfer.rules.SwapSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Infer {
  private static final List<DateElement> DATE_ELEMENTS = new ArrayList<>();
  private static final List<ConditionClause> RULES = new ArrayList<>();

  private enum characterClass {
    STRING,
    DIGIT,
    PUNCT,
    WHITESPACE,
    OTHER
  }


  public Infer() {
    DATE_ELEMENTS.add(new AMPM());
    DATE_ELEMENTS.add(new MonthNum());
    DATE_ELEMENTS.add(new Hour12());
    DATE_ELEMENTS.add(new Hour24());
    DATE_ELEMENTS.add(new DayOfMonth());
    DATE_ELEMENTS.add(new Minute());
    DATE_ELEMENTS.add(new Second());
    DATE_ELEMENTS.add(new Year2());
    DATE_ELEMENTS.add(new Year4());
    DATE_ELEMENTS.add(new UTCOffset());
    DATE_ELEMENTS.add(new MonthTextShort());
    DATE_ELEMENTS.add(new MonthTextLong());
    DATE_ELEMENTS.add(new WeekdayShort());
    DATE_ELEMENTS.add(new WeekdayLong());
    DATE_ELEMENTS.add(new Timezone());

    RULES.add(
      new If(new Sequence(new MonthNum(), new Filler(":"), new SingleDigit(), new Filler(":"), new SingleDigit()),
        new SwapSequence(
          Arrays.asList(new MonthNum(), new Filler(":"), new SingleDigit(), new Filler(":"), new SingleDigit()),
          Arrays.asList(new Hour12(), new Filler(":"), new Minute(), new Filler(":"), new Second())
        ))
    );

    RULES.add(
      new If(new Sequence(new Hour24(), new Filler(":"), new SingleDigit(), new Filler(":"), new SingleDigit()),
        new SwapSequence(
          Arrays.asList(new Hour24(), new Filler(":"), new SingleDigit(), new Filler(":"), new SingleDigit()),
          Arrays.asList(new Hour24(), new Filler(":"), new Minute(), new Filler(":"), new Second())
        ))
    );

    RULES.add(
      new If(new Sequence(new MonthNum(), new Filler(":"), new SingleDigit(), new NonDigit()),
        new SwapSequence(
          Arrays.asList(new MonthNum(), new Filler(":"), new NonDigit()),
          Arrays.asList(new Hour12(), new Filler(":"), new Minute())
        ))
    );

    RULES.add(
      new If(new Sequence(new Hour24(), new Filler(":"), new SingleDigit(), new NonDigit()),
        new SwapSequence(
          Arrays.asList(new Hour24(), new Filler(":"), new NonDigit()),
          Arrays.asList(new Hour24(), new Filler(":"), new Minute())
        ))
    );

    RULES.add(
      new If(
        new And(
          new Sequence(new Hour12(), new Filler(":"), new Minute()),
          new Contains((ConditionClause) Arrays.asList(new Hour24()))
        ),
        new Swap(new Hour24(), new DayOfMonth())
      )
    );

    RULES.add(new If(
      new And(
        new Sequence(new Hour12(), new Filler(":"), new Minute()),
        new Duplicate(new Hour12())
      ),
      new SwapDuplicateWhereSequenceNot(new Hour12(),
        new DayOfMonth(),
        Arrays.asList(new Hour12(), new Filler(":"))
      ))
    );

    RULES.add(new If(
      new And(
        new Sequence(new Hour24(), new Filler(":"), new Minute()),
        new Duplicate(new Hour24())
      ),
      new SwapDuplicateWhereSequenceNot(new Hour24(),
        new DayOfMonth(),
        Arrays.asList(new Hour24(), new Filler(":"))
      ))
    );

    RULES.add(
      new If(
        new Contains((ConditionClause) Arrays.asList(new MonthNum(), new MonthTextLong())),
        new Swap(new MonthNum(), new DayOfMonth())
      )
    );

    RULES.add(
      new If(
        new Contains((ConditionClause) Arrays.asList(new MonthNum(), new MonthTextShort())),
        new Swap(new MonthNum(), new DayOfMonth())
      )
    );

    RULES.add(new If(
      new Sequence(new MonthNum(), new Filler("."), new Hour12()),
      new SwapSequence(
        Arrays.asList(new MonthNum(), new Filler("."), new Hour12()),
        Arrays.asList(new MonthNum(), new KeepOriginal(), new DayOfMonth()))
    ));

    RULES.add(new If(
      new Sequence(new MonthNum(), new Filler("."), new Hour24()),
      new SwapSequence(
        Arrays.asList(new MonthNum(), new Filler("."), new Hour24()),
        Arrays.asList(new MonthNum(), new KeepOriginal(), new DayOfMonth()))
    ));

    RULES.add(new If(
      new Sequence(
        new Hour12(), new Filler("."), new MonthNum()),
      new SwapSequence(
        Arrays.asList(new Hour24(), new Filler("."), new MonthNum()),
        Arrays.asList(new DayOfMonth(), new KeepOriginal(), new MonthNum())
      ))
    );

    RULES.add(new If(
      new Sequence(
        new Hour24(), new Filler("."), new MonthNum()),
      new SwapSequence(
        Arrays.asList(new Hour24(), new Filler("."), new MonthNum()),
        Arrays.asList(new DayOfMonth(), new KeepOriginal(), new MonthNum())
      ))
    );

    RULES.add(
      new If( new Duplicate(new MonthNum()), new Swap(new MonthNum(), new DayOfMonth()))
    );

    RULES.add(
      new If(
        new Sequence( new Filler("+"), new Year4()),
        new SwapSequence(
          Arrays.asList(new Filler("+"), new Year4()),
          Arrays.asList(new UTCOffset(), (DateElement)null)
        )
      )
    );

    RULES.add(
      new If(
        new Sequence(new Filler("-"), new Year4()),
        new SwapSequence(
          Arrays.asList(new Filler("+"), new Year4()),
          Arrays.asList(new UTCOffset(), (DateElement)null)
        )
      )
    );
  }

  /**
   * Returns a datetime.strptime-compliant format string for parsing the *most likely* date format
   * used in examples. examples is a list containing example date strings.
   * @param examples An ArrayList of example Stings
   * @return the most likely date format
   */
  public String infer(ArrayList<String> examples, List<ConditionClause> altRules) {
    StringBuilder dateString = new StringBuilder();
    List<DateElement> dateClasses = tagMostLikely(examples);

    if (altRules != null) {
      dateClasses = applyRewrites(dateClasses, altRules);
    } else {
      dateClasses = applyRewrites(dateClasses, RULES);
    }

    if (dateClasses == null) {
      return "";
    }

    for (DateElement element : dateClasses) {
      dateString.append(element.directive);
    }
    return dateString.toString();
  }


  /**
   * Return a list of date elements by applying rewrites to the initial date element list
   * @return a list of date elements after rewriting the elements
   */
  private List<DateElement> applyRewrites(List<DateElement> dateClasses, List<?> altRules) {
    // TODO Implement this
    for (ConditionClause rule : RULES) {
      List<DateElement> result = ((If) rule).execute(dateClasses);
      dateClasses.addAll(result);
    }
    return dateClasses;
  }

  /**
   * Return the date_elem that has the most restrictive range from date_elems
   * @param elements
   * @return
   */
  public static DateElement mostRestrictive(List<DateElement> elements) throws IllegalStateException {
    int mostIndex = DATE_ELEMENTS.size();
    for (DateElement element : elements) {
      if (DATE_ELEMENTS.contains(element) && DATE_ELEMENTS.indexOf(element) < mostIndex) {
        mostIndex = DATE_ELEMENTS.indexOf(element);
      }
    }
    if (mostIndex < DATE_ELEMENTS.size()) {
      return DATE_ELEMENTS.get(mostIndex);
    } else {
      throw new IllegalStateException("No least restrictive date element found.");
    }
  }

  /**
   * For each date class, return the percentage of tokens that the class matched (floating point [0.0 - 1.0]). The
   * returned value is a tuple of length patterns. Tokens should be a list.
   * @param dateClasses
   * @param tokens
   */
  protected static List<Double> percentMatch (List<DateElement> dateClasses, List<String> tokens) {
    List<Integer> matchCount = new ArrayList<>(Collections.nCopies(dateClasses.size(), 0));
    List<Double> percentages = new ArrayList<>();

    for (int i = 0; i < dateClasses.size(); i++) {
      DateElement element = dateClasses.get(i);
      for (String token : tokens) {
        if (element.isMatch(token)) {
          matchCount.set(i, matchCount.get(i) + 1);
        }
      }
    }

    for (Integer m : matchCount) {
      percentages.add(m.doubleValue() / tokens.size());
    }
    return percentages;
  }


  protected static characterClass getCharacterClass (char c) {
    if (Character.isAlphabetic(c)) {
      return characterClass.STRING;
    } else if (Character.isDigit(c)) {
      return characterClass.DIGIT;
    } else if (Character.isWhitespace(c)) {
      return characterClass.WHITESPACE;
    } else if (Pattern.matches("\\p{Punct}", String.valueOf(c))) {
      return characterClass.PUNCT;
    } else {
      return characterClass.OTHER;
    }
  }

  /**
   * Return a list of date elements by choosing the most likely element for a token within examples (context-free).
   * @param examples List of most likely elements
   */
  protected static List<DateElement> tagMostLikely(List<String> examples) {
    List<String> tokenizedExamples = new ArrayList<>();
    List<Integer> tokenLengths = new ArrayList<>();
    List<DateElement> mostLikely = new ArrayList<>();

    // We currently need the tokenized_examples to all have the same length, so drop instances that have a length
    // that does not equal the mode of lengths within tokenized_examples
    for (String example : examples) {
      List<String> tokens = tokenizeByCharacterClass(example);
      for (String token :  tokens) {
        tokenizedExamples.add(token);
        tokenLengths.add(token.length());
      }
    }

    int tokenLengthsMode = mode(tokenLengths);
    Iterator<String> iter = tokenizedExamples.iterator();
    String example;
    while(iter.hasNext()) {
      example = iter.next();
      if (example.length() != tokenLengthsMode) {
          iter.remove();
      }
    }

    // Now, we iterate through the tokens, assigning date elements based on their likelihood. In cases where
    // the assignments are unlikely for all date elements, assign filler.
    for (int i =0; i < tokenLengthsMode; i++) {
      List<String> tokens = new ArrayList<>();
      for (String token : tokenizedExamples) {
        tokens.add(i, token);
      }

      List<Double> probabilities = percentMatch(DATE_ELEMENTS, tokens);
      double maxProbability = max(probabilities);
      if (maxProbability < 0.5) {
        mostLikely.add(new Filler(mostFrequent(tokens)));
      } else {
        // TODO Start here...
      }
    }

    return mostLikely;
  }

  protected static List<String> tokenizeByCharacterClass(String text) {
    List<String> result = new ArrayList<>();

    String token = "";
    characterClass previousClass;
    characterClass currentClass = null;
    StringBuilder tokenBuider = null;

    for (int i = 0; i < text.length(); i++) {
      if (i == 0) {
        currentClass = getCharacterClass(text.charAt(i));
        tokenBuider = new StringBuilder();
        tokenBuider.append(text.charAt(i));
        continue;
      } else {
        previousClass = currentClass;
        currentClass = getCharacterClass(text.charAt(i));
      }

      if (previousClass == currentClass) {
        tokenBuider.append(text.charAt(i));
      } else {
        token = tokenBuider.toString();
        result.add(token);
        tokenBuider = new StringBuilder();

        // Add new token
        tokenBuider.append(text.charAt(i));
      }
    }
    token = tokenBuider.toString();
    result.add(token);

    return result;
  }

  protected static double max(List<Double> percentages) {
    double max = 0.0;
    for (Double d : percentages) {
      if (d > max) {
        max = d;
      }
    }
    return max;
  }

  protected static int mode(List<Integer> array) {
    int mode = array.get(0);
    int maxCount = 0;
    for (int i = 0; i < array.size(); i++) {
      int value = array.get(i);
      int count = 0;
      for (Integer integer : array) {
        if (integer == value) count++;
        if (count > maxCount) {
          mode = value;
          maxCount = count;
        }
      }
    }
    if (maxCount > 1) {
      return mode;
    }
    return 0;
  }

  protected static String mostFrequent(List<String> array) {
    String mode = array.get(0);
    int maxCount = 0;
    for (int i = 0; i < array.size(); i++) {
      String value = array.get(i);
      int count = 0;
      for (String s : array) {
        if (s.compareTo(value)==0) count++;
        if (count > maxCount) {
          mode = value;
          maxCount = count;
        }
      }
    }
    if (maxCount > 1) {
      return mode;
    }
    return "";
  }
}
