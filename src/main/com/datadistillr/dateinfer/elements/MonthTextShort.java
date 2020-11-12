package com.datadistillr.dateinfer.elements;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class MonthTextShort extends DateElement {
  public MonthTextShort() {
    this.directive = "%b";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Map<String, Integer> months = Calendar.getInstance().getDisplayNames(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
    return months.containsKey(token);
  }
}
