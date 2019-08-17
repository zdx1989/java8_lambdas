package chap4;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;

public class TypeExample {

    @FunctionalInterface
    private interface IntPredicate {
        public boolean test(int value);
    }

    private static void overloadMethod(IntPredicate predicate) {
        System.out.println("IntPredicate");
    }

    private static void overloadMethod(Predicate<Integer> predicate) {
        System.out.println("Predicate");
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("zdx", "ygy", "cwj", "lp");
        IntSummaryStatistics nameLengthStatistic = names
                .stream()
                .mapToInt(String::length)
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Avg: %f, Sum: %d",
                nameLengthStatistic.getMax(),
                nameLengthStatistic.getMin(),
                nameLengthStatistic.getAverage(),
                nameLengthStatistic.getSum());

        //overloadMethod(x -> x > 1);
    }
}



