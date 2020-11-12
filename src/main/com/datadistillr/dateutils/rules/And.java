package com.datadistillr.dateutils.rules;

import com.datadistillr.dateutils.elements.DateElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class And extends ConditionClause{

  private final List<ConditionClause> conditions;

  /**
   * Returns true if all conditions are true.
   */
  public And (ConditionClause ... requirements) {
    this.conditions = new ArrayList<>(Arrays.asList(requirements));
  }

  @Override
  public boolean isTrue(List<DateElement> elementList) {
    for (ConditionClause clause : conditions) {
      if (! clause.isTrue(elementList)) {
        return false;
      }
    }
    return true;
  }
}
