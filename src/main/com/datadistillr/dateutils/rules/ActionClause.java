package com.datadistillr.dateutils.rules;

import com.datadistillr.dateutils.elements.DateElement;

import java.util.List;

public abstract class ActionClause {
  private List<DateElement> elementList;

  /**
   *
   * @param before The list before the transformation
   * @return Return a new instance of elem_list permuted by the action
   */
  public abstract List<DateElement> act(List<DateElement> before);
}
