package com.datadistillr.dateutils.elements;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class WeekdayShort extends DateElement {
  public WeekdayShort() {
    this.directive = "%a";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Map<String, Integer> months = Calendar.getInstance().getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    return months.containsKey(token);
  }
}
