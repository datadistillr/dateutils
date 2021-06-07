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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Next extends ConditionClause {

  private final DateElement first;
  private final DateElement second;

  /**
   * Return true if A and B are found next to each other in the elem_list (with zero or more Filler elements
   * between them).
   * @param first The first element
   * @param second The second Date element
   */
  public Next (DateElement first, DateElement second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean isTrue(List<DateElement> elementList) {
    List<Integer> firstPositions = new ArrayList<>();
    List<Integer> secondPositions = new ArrayList<>();

    DateElement currentElement;
    for (int i = 0; i < elementList.size(); i++) {
       currentElement = elementList.get(i);
       if (currentElement == first) {
         firstPositions.add(i);
       } else if (currentElement == second) {
         secondPositions.add(i);
       }
    }

    int left;
    int right;
    for (Integer aPosition : firstPositions) {
      for (Integer bPosition : secondPositions) {
        left = Math.min(aPosition, bPosition);
        right = Math.max(aPosition, bPosition);

        List<DateElement> betweenList = elementList.subList(left+1, right-1);
        if (betweenList.size() == 0) {
          return true;
        } else if (allFiller(elementList)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean allFiller( List<DateElement> elementList) {
    for (DateElement element : elementList) {
      if (!element.isFiller()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Next other = (Next) obj;
    return Objects.equals(first, other.first) &&
      Objects.equals(second, other.second);
  }
}
