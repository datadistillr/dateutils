package com.datadistillr.dateutils.elements;

public class Hour12 extends DateElement {
  public Hour12() {
    directive = "%I";
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
