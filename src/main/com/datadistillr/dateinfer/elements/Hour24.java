package com.datadistillr.dateinfer.elements;

public class Hour24 extends DateElement {
  public Hour24() {
    directive = "%H";
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
      return (tokenValue >= 0 && tokenValue <= 23);
    } catch (Exception e) {
      return false;
    }
  }
}
