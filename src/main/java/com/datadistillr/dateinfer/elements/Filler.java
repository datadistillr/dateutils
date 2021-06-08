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

package com.datadistillr.dateinfer.elements;

public class Filler extends DateElement{

  /**
   * A special date class, filler matches everything. Filler is usually used for matches of whitespace
   * and punctuation.
   * @param filler The filler token
   */
  public Filler (String filler) {
    directive = filler.replace("%", "%%");
  }

  @Override
  public boolean isMatch(String token) {
    return true;
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isFiller() { return true; }
}
