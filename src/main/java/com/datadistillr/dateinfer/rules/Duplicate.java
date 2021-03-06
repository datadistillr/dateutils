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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Duplicate extends ConditionClause{

  private final DateElement element;

  /**
   * Returns true if there is more than one instance of elem in elem_list.
   */
  public Duplicate (DateElement element) {
    this.element = element;
  }

  @Override
  public boolean isTrue(List<DateElement> elementList) {
    if (Collections.frequency(elementList, element ) > 1)  {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(element);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Duplicate other = (Duplicate) obj;
    return Objects.equals(element, other.element);
  }
}
