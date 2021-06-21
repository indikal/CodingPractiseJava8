package lk.inli.hackerrank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

public class NonDivisibleSubsetTest {
  @Test
  public void singleElementArray() {
    assertEquals(1, NonDivisibleSubset.nonDivisibleSubset(1, Arrays.asList(1)));
  }

  @Test //1min 1s
  public void largeArray() {
    List<Integer> s = IntStream.range(1, (int) Math.pow(10, 5))
        .boxed()
        .collect(Collectors.toList());
    assertNotEquals(0, NonDivisibleSubset.nonDivisibleSubset(4, s));
  }

  @Test
  public void _4ElementArray() {
    List<Integer> s = Arrays.asList(1, 7, 2, 4);
    assertEquals(3, NonDivisibleSubset.nonDivisibleSubset(3, s));
  }

  @Test
  public void _7ElementArray() {
    List<Integer> s = Arrays.asList(19, 10, 12, 24, 25, 22);
    assertEquals(3, NonDivisibleSubset.nonDivisibleSubset(4, s));
  }

  @Test
  public void _15ElementArray() {
    List<Integer> s = Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436);
    assertEquals(11, NonDivisibleSubset.nonDivisibleSubset(7, s));
  }
}
