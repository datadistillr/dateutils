package com.datadistillr.dateinfer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TestInferMethods {

  @Test
  public void TestStringTokenizer() {
    List<String> result = Infer.tokenizeByCharacterClass("2013-08-14");

    List<String> actual = new ArrayList<>();
    actual.add("2013");
    actual.add("-");
    actual.add("08");
    actual.add("-");
    actual.add("14");
    assertIterableEquals(actual, result);

    List<String> result2 = Infer.tokenizeByCharacterClass("Sat Jan 11 19:54:52 MST 2014");
    System.out.println(result2);

  }

  @Test
  public void TestMostLikely() {
    List<String> examples = new ArrayList<>();
    examples.add("2020-01-01");
    examples.add("2019-12-31");
    Infer.tagMostLikely(examples);
  }
}
