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

public class SwapSequence extends ActionClause {

  private final List<DateElement> findSequence;
  private final List<DateElement> swapSequence;

  public SwapSequence (List<DateElement> findSequence, List<DateElement> swapSequence) {
    this.findSequence = findSequence;
    this.swapSequence = swapSequence;
  }

  @Override
  public List<DateElement> act (List<DateElement> elementList) {
    List<DateElement> copy = new ArrayList<>(elementList);

    int startPosition = Sequence.find(findSequence, copy);
    for (int i = 0; i < swapSequence.size(); i++) {
      DateElement replacement = swapSequence.get(i);
      if (! replacement.isKeepOriginal()) {
        copy.set(startPosition + i, replacement);
      }
    }
    return copy;
  }
}
