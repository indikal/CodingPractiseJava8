package lk.inli.hackerrank;

import java.io.*;
    import java.math.*;
    import java.security.*;
    import java.text.*;
    import java.util.*;
    import java.util.concurrent.*;
    import java.util.function.*;
    import java.util.regex.*;
    import java.util.stream.*;
    import static java.util.stream.Collectors.joining;
    import static java.util.stream.Collectors.toList;

class RepeatedString {

  private static final char GIVEN_CHAR = 'a';
  /*
   * Complete the 'repeatedString' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. LONG_INTEGER n
   */

  public static long repeatedString(String s, long n) {
    // Write your code here
    long noOfBlocks = n / s.length();
    int remainderLength = (int) (n % s.length()); //since |s| <= 100, casting won't loss precision
    String remainderString = s.substring(0, remainderLength);

    long totalRepetitions = (getNoOfCharsInGivenString(s) * noOfBlocks) + getNoOfCharsInGivenString(remainderString);
    return totalRepetitions;
  }

  private static long getNoOfCharsInGivenString(String s) {
    return s.chars()
        .filter(c -> c == GIVEN_CHAR)
        .count();
  }
}