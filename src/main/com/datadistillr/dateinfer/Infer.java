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
import com.datadistillr.dateinfer.elements.Hour12;
import com.datadistillr.dateinfer.elements.Hour24;
import com.datadistillr.dateinfer.elements.Minute;
import com.datadistillr.dateinfer.elements.MonthNum;
import com.datadistillr.dateinfer.elements.MonthTextLong;
import com.datadistillr.dateinfer.elements.MonthTextShort;
import com.datadistillr.dateinfer.elements.Second;
import com.datadistillr.dateinfer.elements.Timezone;
import com.datadistillr.dateinfer.elements.UTCOffset;
import com.datadistillr.dateinfer.elements.WeekdayLong;
import com.datadistillr.dateinfer.elements.WeekdayShort;
import com.datadistillr.dateinfer.elements.Year2;
import com.datadistillr.dateinfer.elements.Year4;
import com.datadistillr.dateinfer.rules.ActionClause;
import com.datadistillr.dateinfer.rules.ConditionClause;

import java.util.ArrayList;
import java.util.List;

public class Infer {
  private static final List<DateElement> DATE_ELEMENTS = new ArrayList<>();
  private static final List<ConditionClause> RULES = new ArrayList<>();

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


/*
RULES = [
    If(Sequence(MonthNum, F(':'), '\d', F(':'), '\d'),
       SwapSequence([MonthNum, F(':'), '\d', F(':'), '\d'], [Hour12, F(':'), Minute, F(':'), Second])),
    If(Sequence(Hour24, F(':'), '\d', F(':'), '\d'),
       SwapSequence([Hour24, F(':'), '\d', F(':'), '\d'], [Hour24, F(':'), Minute, F(':'), Second])),
    If(Sequence(MonthNum, F(':'), '\d', '\D'),
       SwapSequence([MonthNum, F(':'), '.'], [Hour12, F(':'), Minute])),
    If(Sequence(Hour24, F(':'), '\d', '\D'),
       SwapSequence([Hour24, F(':'), '\d'], [Hour24, F(':'), Minute])),
    If(And(
        Sequence(Hour12, F(':'), Minute),
        Contains(Hour24)),
       Swap(Hour24, DayOfMonth)
    ),
    If(And(
        Sequence(Hour12, F(':'), Minute),
        Duplicate(Hour12)),
       SwapDuplicateWhereSequenceNot(Hour12, MonthNum, (Hour12, F(':')))
    ),
    If(And(
        Sequence(Hour24, F(':'), Minute),
        Duplicate(Hour24)),
       SwapDuplicateWhereSequenceNot(Hour24, DayOfMonth, [Hour24, F(':')])
    ),
    If(Contains(MonthNum, MonthTextLong), Swap(MonthNum, DayOfMonth)),
    If(Contains(MonthNum, MonthTextShort), Swap(MonthNum, DayOfMonth)),
    If(Sequence(MonthNum, '.', Hour12), SwapSequence([MonthNum, '.', Hour12], [MonthNum, KeepOriginal, DayOfMonth])),
    If(Sequence(MonthNum, '.', Hour24), SwapSequence([MonthNum, '.', Hour24], [MonthNum, KeepOriginal, DayOfMonth])),
    If(Sequence(Hour12, '.', MonthNum), SwapSequence([Hour24, '.', MonthNum], [DayOfMonth, KeepOriginal, MonthNum])),
    If(Sequence(Hour24, '.', MonthNum), SwapSequence([Hour24, '.', MonthNum], [DayOfMonth, KeepOriginal, MonthNum])),
    If(Duplicate(MonthNum), Swap(MonthNum, DayOfMonth)),
    If(Sequence(F('+'), Year4), SwapSequence([F('+'), Year4], [UTCOffset, None])),
    If(Sequence(F('-'), Year4), SwapSequence([F('+'), Year4], [UTCOffset, None]))
]
 */


  }

  /**
   * Returns a datetime.strptime-compliant format string for parsing the *most likely* date format
   * used in examples. examples is a list containing example date strings.
   * @param examples An ArrayList of example Stings
   * @return the most likely date format
   */
  public String infer(ArrayList<String> examples) {
    StringBuilder dateString = new StringBuilder();
    List<DateElement> dateClasses = tagMostLikely(examples);

    dateClasses = applyRewrites(dateClasses, RULES);

    for (DateElement element : dateClasses) {
      dateString.append(element.directive);
    }

    return dateString.toString();
  }

  private List<DateElement> tagMostLikely(ArrayList<String> examples) {

  }

  private List<DateElement> applyRewrites(ArrayList<DateElement>) {
    for (ActionClause clause : RULES) {

    }
  }

}
