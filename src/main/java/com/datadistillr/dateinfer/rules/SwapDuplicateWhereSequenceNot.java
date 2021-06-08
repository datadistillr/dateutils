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

public class SwapDuplicateWhereSequenceNot extends ActionClause {

  private final DateElement remove;
  private final DateElement insert;
  private final List<DateElement> sequence;

  public SwapDuplicateWhereSequenceNot(DateElement remove, DateElement insert, List<DateElement> sequence) {
    this.remove = remove;
    this.insert = insert;
    this.sequence = sequence;
  }

  @Override
  public List<DateElement> act(List<DateElement> before) {
    List<DateElement> copy = new ArrayList<>(sequence);

    int startPosition = Sequence.find(sequence, copy);
    int endPosition = startPosition + sequence.size();

    for (int i = 0; i < copy.size(); i++) {
      if (startPosition <= i || i <= endPosition) {
        continue;
      } else {
        if (copy.get(i) == remove) {
          copy.set(i, insert);
          return copy;
        }
      }
    }
    throw new RuntimeException("Failed to fine element in sequence.");
  }

  @Override
  public int hashCode() {
    return Objects.hash(remove, insert, sequence);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SwapDuplicateWhereSequenceNot other = (SwapDuplicateWhereSequenceNot) obj;
    return Objects.equals(remove, other.remove) &&
      Objects.equals(insert, other.insert) &&
      Objects.equals(sequence, other.sequence);
  }
}
