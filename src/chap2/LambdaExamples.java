package chap2;

import javax.swing.text.DateFormatter;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExamples {

    public static void main(String[] args) {
        Runnable noArgument = () -> System.out.println("Hello World");
        ActionListener oneArgument = event -> System.out.println("button click");
        Runnable multiStatement = () -> {
            System.out.println("Hello");
            System.out.println("World");
        };
        BinaryOperator<Long> add = (x, y) -> x + y;
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
        Predicate<Integer> atLeast5 = x -> x > 5;
        Function<Integer, String> int2String = i -> i.toString();

        Function<Long, Long> add1 = x -> x + 1;

        ThreadLocal<DateFormatter> df = ThreadLocal.withInitial(() -> new DateFormatter());

    }

}

