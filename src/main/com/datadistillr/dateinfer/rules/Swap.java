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

public class Swap extends ActionClause {
  private final DateElement remove;
  private final DateElement insert;

  public Swap(DateElement remove, DateElement insert) {
    this.remove = remove;
    this.insert = insert;
  }

  @Override
  public List<DateElement> act(List<DateElement> elementList) {
    List<DateElement> copy = new ArrayList<>(elementList);
    int index = copy.indexOf(remove);
    copy.set(index, insert);
    return copy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(remove, insert);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Swap other = (Swap) obj;
    return Objects.equals(remove, other.remove) &&
      Objects.equals(insert, other.insert);
  }
}
