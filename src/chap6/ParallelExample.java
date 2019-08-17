package chap6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelExample {

    public static void main(String[] args) {
        IntStream intStream1 = IntStream.of(1, 2, 3);
        IntStream intStream2 = IntStream.of(1, 2, 3);
        int res1 = seqSumOfSquares(intStream1);
        int res2 = seqSumOfSquares1(intStream2);
        System.out.println(res1);
        System.out.println(res2);

        List<Integer> integerList = Arrays.asList(1, 2, 3);
        int res3 = multiplyThrough(integerList);
        int res4 = multiplyThrough1(integerList);
        System.out.println(res3);
        System.out.println(res4);

        int res5 = slowSumOfSquares(integerList);
        int res6 = quickSumOfSquare(integerList);
        System.out.println(res5);
        System.out.println(res6);
    }

    public static int seqSumOfSquares(IntStream range) {
        return range.map(i -> i * i)
                .sum();
    }

    public static int seqSumOfSquares1(IntStream range) {
        return range.parallel()
                .map(i -> i * i)
                .sum();
    }

    public static int multiplyThrough(List<Integer> list) {
        return list.stream()
                .reduce(5, (acc, item) -> acc * item);
    }

    public static int multiplyThrough1(List<Integer> list) {
        return list.parallelStream()
                .reduce(1, (acc, item) -> acc * item) * 5;
    }

    public static int slowSumOfSquares(List<Integer> list) {
        return list.parallelStream()
                .map(x -> x * x)
                .reduce(0, (acc, item) -> acc + item);
    }

    public static int quickSumOfSquare(List<Integer> list) {
        return list.parallelStream()
                .mapToInt(x -> x * x)
                .reduce(0, (acc, item) -> acc + item);
    }
}
