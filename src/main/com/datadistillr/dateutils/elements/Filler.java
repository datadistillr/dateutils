package com.datadistillr.dateutils.elements;

public class Filler extends DateElement{

  /**
   * A special date class, filler matches everything. Filler is usually used for matches of whitespace
   * and punctuation.
   * @param filler The filler token
   */
  public Filler (String filler) {
    directive = filler.replace("%", "%%");
  }

  @Override
  public boolean isMatch(String token) {
    return true;
  }

  @Override
  public boolean isNumerical() {
    return false;
  }
}
