package com.datadistillr.dateutils.rules;

import com.datadistillr.dateutils.elements.DateElement;

import java.util.List;

public abstract class ConditionClause {
  public abstract boolean isTrue(List<DateElement> elementList);
}
