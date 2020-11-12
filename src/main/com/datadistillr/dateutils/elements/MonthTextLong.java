package com.datadistillr.dateutils.elements;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class MonthTextLong extends DateElement {
  public MonthTextLong() {
    this.directive = "%B";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Map<String, Integer> months = Calendar.getInstance().getDisplayNames(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    return months.containsKey(token);
  }
}
