package com.datadistillr.dateinfer.elements;

public class Year4 extends DateElement {
  public Year4() {
    directive = "%Y";
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
      return (tokenValue >= 0 && tokenValue <= 9999);
    } catch (Exception e) {
      return false;
    }
  }
}
