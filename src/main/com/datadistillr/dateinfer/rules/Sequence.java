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
import java.util.Arrays;
import java.util.List;

public class Sequence extends ConditionClause {

  private final List<DateElement> sequence;
  /**
   * Returns true if the given sequence is found in elem_list. The sequence consists of date elements
   * and wild cards.
   *
   * @param sequence A list of DateElements
   */
  public Sequence (DateElement ... sequence) {
    this.sequence = new ArrayList<>(Arrays.asList(sequence));
  }

  @Override
  public boolean isTrue(List<DateElement> elementList) {
    int position = 0;
    for (DateElement element : elementList) {
      if ( match(element, sequence.get(position))) {
        position += 1;
        if (position == sequence.size()) {
          return true;
        }
      } else {
        position = 0;
      }
    }
    return false;
  }

  /**
   * Return True if elem (an element of elem_list) matches seq_expr, an element in self.sequence
   * @param element
   * @param sequenceExpression
   * @return true if the element matches, false if not
   */
  public static boolean match (DateElement element, DateElement sequenceExpression) {
    /*if () {

    } else {
      return element == sequenceExpression;
    }*/

    // TODO Fix this..
    return true;

  }

  public static int find (List<DateElement>findSequence, List<DateElement> elementList) {
    int sequencePosition = 0;

    DateElement element = null;
    for (int i = 0; i < elementList.size(); i++) {
      element = elementList.get(i);
      if (Sequence.match(element, findSequence.get(i))) {
        sequencePosition += 1;
        if (sequencePosition == findSequence.size()) {
          return i - sequencePosition + 1;
        }
      } else {
        sequencePosition = 0;
      }
    }

    throw new RuntimeException("Failed to find sequence " + element + " in element list.");
  }

}
