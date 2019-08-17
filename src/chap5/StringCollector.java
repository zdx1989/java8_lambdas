package chap5;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String, StringCombine, String> {

    private String delimited;

    private String prefix;

    private String suffix;

    public StringCollector(String delimited, String prefix, String suffix) {
        this.delimited = delimited;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Supplier<StringCombine> supplier() {
        return () -> new StringCombine(delimited, prefix, suffix);
    }

    @Override
    public BiConsumer<StringCombine, String> accumulator() {
        return StringCombine::add;
    }

    @Override
    public BinaryOperator<StringCombine> combiner() {
        return StringCombine::megre;
    }

    @Override
    public Function<StringCombine, String> finisher() {
        return StringCombine::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }
}
