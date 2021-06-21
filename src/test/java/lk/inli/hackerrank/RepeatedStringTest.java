package lk.inli.hackerrank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RepeatedStringTest {

  @Test
  public void testSingleChar() {
    assertEquals(1, RepeatedString.repeatedString("a", 1));
  }

  @Test
  public void testSmallString() {
    assertEquals(4, RepeatedString.repeatedString("abcac", 10));
  }

  @Test
  public void testMaxStringLength() {
    assertEquals(1, RepeatedString.repeatedString(new String(new char[100]).replace("\0", "a"), 1));
  }

  @Test
  public void testMaxStringLength2() {
    assertEquals(100, RepeatedString.repeatedString(new String(new char[100]).replace("\0", "a"), 100));
  }

  @Test
  public void testMaxStringLengthN() {
    long n = (long) Math.pow(10, 12);
    assertEquals(n, RepeatedString.repeatedString(
        new String(new char[100]).replace("\0", "a"),
        n));
  }
}
