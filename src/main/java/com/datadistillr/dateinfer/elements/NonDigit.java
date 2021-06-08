package com.datadistillr.dateinfer.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonDigit extends DateElement {

  /**
   * This class represents a single non-digit
   */
  public NonDigit() {
    directive = "\\D";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Pattern pattern = Pattern.compile("^\\D$");
    Matcher matcher = pattern.matcher(token);
    return matcher.find();
  }
}

