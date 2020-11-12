package com.datadistillr.dateinfer.elements;

import java.time.ZoneId;
import java.util.Set;

public class Timezone extends DateElement {

  public Timezone() {
    this.directive = "%Z";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    Set<String> zones = ZoneId.getAvailableZoneIds();
    return zones.contains(token);
  }
}
