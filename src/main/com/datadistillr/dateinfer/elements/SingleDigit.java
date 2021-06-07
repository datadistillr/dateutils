package com.datadistillr.dateinfer.elements;

public class SingleDigit extends DateElement {

  /**
   * This class represents a single digit
   */
  public SingleDigit() {
    directive = "\\d";
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
      return (tokenValue >= 0 && tokenValue <= 9);
    } catch (Exception e) {
      return false;
    }
  }
}
