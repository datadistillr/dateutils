package com.datadistillr.dateutils.elements;

public class AMPM extends DateElement {

  public AMPM() {
    this.directive = "%p";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    token = token.toLowerCase();
    return (token.equals("am") || token.equals("pm"));
  }
}
