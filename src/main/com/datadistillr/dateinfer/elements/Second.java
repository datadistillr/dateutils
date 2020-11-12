package com.datadistillr.dateinfer.elements;

public class Second extends DateElement {
  /**
   *  Normally, seconds range from 0 to 59. In the case of a leap second, the second value may be 60.
   */
  public Second() {
    directive = "%S";
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
      return (tokenValue >= 0 && tokenValue <= 60);
    } catch (Exception e) {
      return false;
    }
  }
}
