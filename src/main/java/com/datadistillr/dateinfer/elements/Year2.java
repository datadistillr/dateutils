package com.datadistillr.dateinfer.elements;

public class Year2 extends DateElement {
  public Year2() {
    directive = "%y";
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
      return (tokenValue >= 0 && tokenValue <= 99);
    } catch (Exception e) {
      return false;
    }
  }
}
