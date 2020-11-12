package com.datadistillr.dateutils;

import com.datadistillr.dateutils.elements.AMPM;
import com.datadistillr.dateutils.elements.DateElement;
import com.datadistillr.dateutils.elements.DayOfMonth;
import com.datadistillr.dateutils.elements.Hour12;
import com.datadistillr.dateutils.elements.Hour24;
import com.datadistillr.dateutils.elements.Minute;
import com.datadistillr.dateutils.elements.MonthNum;
import com.datadistillr.dateutils.elements.MonthTextLong;
import com.datadistillr.dateutils.elements.MonthTextShort;
import com.datadistillr.dateutils.elements.Second;
import com.datadistillr.dateutils.elements.Timezone;
import com.datadistillr.dateutils.elements.UTCOffset;
import com.datadistillr.dateutils.elements.WeekdayLong;
import com.datadistillr.dateutils.elements.WeekdayShort;
import com.datadistillr.dateutils.elements.Year2;
import com.datadistillr.dateutils.elements.Year4;
import com.datadistillr.dateutils.rules.ActionClause;
import com.datadistillr.dateutils.rules.ConditionClause;

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
