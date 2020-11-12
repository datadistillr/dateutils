package com.datadistillr.dateutils.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UTCOffset extends DateElement {
  public UTCOffset() {
    directive = "%z";
  }

  @Override
  public boolean isNumerical() {
    return false;
  }

  @Override
  public boolean isMatch(String token) {
    /*
    * technically offset_re should be:
    * ^[-\+]\d\d:?(\d\d)?$
    * but python apparently only uses the +/-hhmm format
    * A rule will catch the preceding + and - and combine the two entries since punctuation and numbers
    * are separated by the tokenizer.
    */
    Pattern pattern = Pattern.compile("^[-\\+]\\d\\d:?(\\d\\d)?$", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(token);
    return matcher.find();
  }
}
