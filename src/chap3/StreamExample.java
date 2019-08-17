package chap3;


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Character;

public class StreamExample {

    public static void main(String[] args) {
        List<String> collected = Stream.of("a", "b", "c", "d")
                                       .collect(Collectors.toList());
        List<String> asList = Arrays.asList("a", "b", "c", "d");
        System.out.println(collected);
        System.out.println(asList);

        List<String> upperList = toUpperCase(asList);
        List<String> upperList1 = asList
                .stream()
                .map(String :: toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperList);
        System.out.println(upperList1);

        List<String> dList = Arrays.asList("1abc", "2ujn", "zdx");
        List<String> filterList = startWithDigit(dList);
        List<String> filterList1 = dList
                .stream()
                .filter(x -> Character.isDigit(x.charAt(0)))
                .collect(Collectors.toList());
        //Function<T， R> -> map -> T => R
        //Predicate<T> -> filter -> T => boolean
        List<Integer> together = Stream
                .of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(List<Integer> :: stream)
                .collect(Collectors.toList());
        System.out.println(together);

        List<Student> students = Arrays.asList(
                new Student("ygy", 35),
                new Student("zdx", 29));

        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge);
        List<Student> ageSortedStudents = students
                .stream()
                .sorted(ageComparator)
                .collect(Collectors.toList());
        System.out.println(ageSortedStudents);

        Optional<Student> minStudent = students
                .stream()
                .min(ageComparator);
        System.out.println(minStudent);


        Optional<Student> maxStudent = students
                .stream()
                .max(ageComparator);
        System.out.println(maxStudent);

        long count = Stream.of(1, 2, 3).count();
        int intCount = Stream.of(1, 2, 3).reduce(0, (x, y) -> x + y);

        List<String> lowList = Arrays.asList("ZDx", "l", "ygy");
        System.out.println(findMostLowCaseStr(lowList));
        System.out.println(findMostLowCaseStr1(lowList));

        List<String> mList = Arrays.asList("zdx", "ygy", "dp");
        List<String> mapList = map(mList.stream(), x -> "Hello " + x);
        List<String> mapList1 = map1(mList.stream(), String :: toUpperCase).collect(Collectors.toList());
        System.out.println(mapList);
        System.out.println(mapList1);
        List<String> filterListT = filter(mList.stream(), x -> x.startsWith("z"));
        List<String> filterListT1 = filter(mList.stream(), x -> x.length() > 2);
        System.out.println(filterListT);
        System.out.println(filterListT1);
    }

    private static List<String> toUpperCase(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String str: list) {
            String upperStr = str.toUpperCase();
            resultList.add(upperStr);
        }
        return resultList;
    }

    private static List<String> startWithDigit(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String str: list) {
            if (Character.isDigit(str.charAt(0))) {
                resultList.add(str);
            }
        }
        return resultList;
    }

    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (x, y) -> x + y);
    }

    public static Long lowCaseCount(String str) {
        return str.chars()
                .filter(x -> Character.isLowerCase(x))
                .count();
    }

    public static Function<String, Long> lowCaseCount = str -> str.chars()
            .filter(x -> Character.isLowerCase(x))
            .count();

    public static Optional<String> findMostLowCaseStr(List<String> list) {

        return list.stream()
                .max(Comparator.comparing(lowCaseCount));
    }

    public static Optional<String> findMostLowCaseStr1(List<String> list) {
        return list.stream()
                .reduce((x, y) -> {
                    if (lowCaseCount.apply(x) > lowCaseCount.apply(y))
                        return x;
                    else
                        return y;
                });
    }

    public static <T, R> List<R> map(Stream<T> stream, Function<T, R> func) {
        List<R> identity = new ArrayList<>();
        BiFunction<List<R>, T, List<R>> accumulator = (lr, t) -> {
            R r = func.apply(t);
            lr.add(r);
            return lr;
        };
        BinaryOperator<List<R>> combine = (lr1, lr2) -> {
            lr1.addAll(lr2);
            return lr1;
        };
       return stream.reduce(identity, accumulator, combine);
    }

    public static <T, R> Stream<R> map1(Stream<T> stream, Function<T, R> func) {
        Stream<R> identity = Stream.empty();
        BiFunction<Stream<R>, T, Stream<R>> accumulator = (sr, t) -> {
            R r = func.apply(t);
            return Stream.concat(sr, Stream.of(r));
        };
        BinaryOperator<Stream<R>> combine = (sr1, sr2) -> Stream.concat(sr1, sr2);
        return stream.reduce(identity, accumulator, combine);
    }

    public static <T> List<T> filter(Stream<T> stream, Predicate<T> func) {
        List<T> indentity = new ArrayList<>();
        BiFunction<List<T>, T, List<T>> accumulator = (lt, t) -> {
            if (func.test(t)) lt.add(t);
            return lt;
        };
        BinaryOperator<List<T>> combine = (lr1, lr2) -> {
            lr1.addAll(lr2);
            return lr1;
        };
        return stream.reduce(indentity, accumulator, combine);
    }

    public static <T> Stream<T> filter1(Stream<T> stream, Predicate<T> func) {
        Stream<T> identity = Stream.empty();
        BiFunction<Stream<T>, T, Stream<T>> accumulator = (st, t) -> {
            if (func.test(t)) st = Stream.concat(st, Stream.of(t));
            return st;
        };
        BinaryOperator<Stream<T>> combine = (st1, st2) -> Stream.concat(st1, st2);
        return stream.reduce(identity, accumulator, combine);
    }
}
class Student {
    private String name;

    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
