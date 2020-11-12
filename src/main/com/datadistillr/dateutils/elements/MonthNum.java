package com.datadistillr.dateutils.elements;

public class MonthNum extends DateElement {
  public MonthNum() {
    directive = "%m";
  }

  @Override
  public boolean isNumerical() {
    return true;
  }

  @Override
  public boolean isMatch(String token) {
    int tokenValue;
    try {
      tokenValue = Integer.parseInt(token);
      return (tokenValue >= 1 && tokenValue <= 12);
    } catch (Exception e) {
      return false;
    }
  }
}
