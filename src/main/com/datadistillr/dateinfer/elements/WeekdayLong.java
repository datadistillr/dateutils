package com.datadistillr.dateinfer.elements;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class WeekdayLong extends DateElement {
  public WeekdayLong() {
    this.directive = "%A";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Map<String, Integer> months = Calendar.getInstance().getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    return months.containsKey(token);
  }
}
