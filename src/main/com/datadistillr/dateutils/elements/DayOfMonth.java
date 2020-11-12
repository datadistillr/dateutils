package com.datadistillr.dateutils.elements;

public class DayOfMonth extends DateElement{
  public DayOfMonth() {
    this.directive = "%d";
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
     return (tokenValue >= 1 && tokenValue <= 31);
    } catch (Exception e) {
      return false;
    }
  }
}
