/*
*  Copyright 2020 DataDistillR Inc.
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*
*/

package com.datadistillr.dateinfer.rules;

import com.datadistillr.dateinfer.elements.DateElement;

import java.util.List;
import java.util.Objects;

public class If extends ConditionClause {
  private final ConditionClause condition;
  private final ActionClause action;

  public If (ConditionClause condition, ActionClause action) {
    this.condition = condition;
    this.action = action;
  }

  public List<DateElement> execute (List<DateElement> elementList) {
    if (condition.isTrue(elementList)) {
      return action.act(elementList);
    } else {
      return elementList;
    }
  }

  @Override
  public boolean isTrue(List<DateElement> elementList) {
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(condition, action);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    If other = (If) obj;
    return Objects.equals(condition, other.condition) &&
      Objects.equals(action, other.action);
  }
}
