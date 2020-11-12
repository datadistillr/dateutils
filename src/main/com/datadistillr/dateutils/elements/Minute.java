package com.datadistillr.dateutils.elements;

public class Minute extends DateElement {
  public Minute() {
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
      return (tokenValue >= 0 && tokenValue <= 59);
    } catch (Exception e) {
      return false;
    }
  }
}
