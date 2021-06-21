package lk.inli.hackerrank;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class NonDivisibleSubset {

  /*
   * Complete the 'nonDivisibleSubset' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. INTEGER_ARRAY s
   */

  public static int nonDivisibleSubset(int k, List<Integer> s) {
    // Write your code here
    // return getLargestNonDivisibleSubsetSize(k, s);
    return getLargestSubsetSize(k, s);
  }

  private static int getLargestSubsetSize(int k, List<Integer> s) {
    int[] remaindersAfterDividedBy_k = new int[k];

    for (Integer s_i : s) {
      remaindersAfterDividedBy_k[s_i % k] = remaindersAfterDividedBy_k[s_i % k] + 1;
    }

    System.out.println(Arrays.toString(remaindersAfterDividedBy_k));
    int count = 0;
    for (int i = 1; i < (k + 1) / 2; ++i) {
      count += Math.max(remaindersAfterDividedBy_k[i], remaindersAfterDividedBy_k[k - i]);
    }
    if (k % 2 == 0) {
      count += remaindersAfterDividedBy_k[k / 2] > 0 ? 1 : 0;
    }
    count += remaindersAfterDividedBy_k[0] > 0 ? 1 : 0;

    System.out.println(count);
    return count;
  }

  /*private static List<Integer> getNonDivisiblesFromGivenIndex(int index, int k, List<Integer> s) {
    List<Integer> subset = IntStream.range(index + 1, s.size()).parallel()
        .filter(i -> ((s.get(index) + s.get(i)) % k) > 0)
        .mapToObj(s::get)
        .collect(Collectors.toList());

    if (subset.size() > 0) {
      subset.add(0, s.get(index));
    }
    System.out.println(index + ": " + subset);
    return subset;
  }

  private static List<Integer> getNonDivisiblesForWholeArray(int k, List<Integer> s) {
    return IntStream.range(0, s.size()-1).parallel()
        .mapToObj(i -> getNonDivisiblesFromGivenIndex(i, k, s))
        .filter(subset -> !subset.isEmpty())
        .map(List::size)
        .sorted()
        .collect(Collectors.toList());
  }

  private static int getLargestNonDivisibleSubsetSize(int k, List<Integer> s) {
    List<Integer> nonDivisibleSubsetSizes = getNonDivisiblesForWholeArray(k, s);
    int largestSubsetSize = 0;

    if (null != nonDivisibleSubsetSizes && nonDivisibleSubsetSizes.size() > 0) {
      largestSubsetSize = nonDivisibleSubsetSizes.get(nonDivisibleSubsetSizes.size() - 1);
    }
    System.out.println("Largest Subset Size: " + largestSubsetSize);
    return largestSubsetSize;
  }*/
}