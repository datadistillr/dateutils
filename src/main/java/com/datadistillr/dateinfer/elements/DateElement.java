package com.datadistillr.dateinfer.elements;


public abstract class DateElement {
  /**
   * Abstract class for a date element, a portion of a valid date/time string
   * Inheriting classes should implement a string 'directive' field that provides the relevant
   * directive for the datetime.strftime/strptime method.
   */
  public String directive;

  /**
   * @return Return true if the written representation of the element are digits
   */
  public abstract boolean isNumerical();

  public abstract boolean isMatch(String token);

  public boolean equals(DateElement other) {
    return this.directive.equals(other.directive);
  }

  public String toString() {
    return this.directive;
  }

  public boolean isFiller() { return false; }

  public boolean isKeepOriginal() { return false; }
}
