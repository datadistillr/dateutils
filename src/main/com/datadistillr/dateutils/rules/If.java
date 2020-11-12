package com.datadistillr.dateutils.rules;

import com.datadistillr.dateutils.elements.DateElement;

import java.util.List;

public class If {
  private final ConditionClause condition;
  private final ActionClause action;

  public If (ConditionClause condition, ActionClause action) {
    this.condition = condition;
    this.action = action;
  }

  public List<?> execute (List<DateElement> elementList) {
    if (condition.isTrue(elementList)) {
      return action.act(elementList);
    } else {
      return elementList;
    }
  }
}
