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

public abstract class ActionClause {
  private List<DateElement> elementList;

  /**
   *
   * @param before The list before the transformation
   * @return Return a new instance of elem_list permuted by the action
   */
  public abstract List<DateElement> act(List<DateElement> before);

  public boolean isKeepOriginal() { return false; }
}
