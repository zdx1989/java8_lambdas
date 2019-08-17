package chap5;

import common.Album;
import common.Artist;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class CollectorExample {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("zdx", "ygy", "cwj");
        List<String> names1 = names.stream().collect(Collectors.toList());
        System.out.println(names1);

        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 2, 1));
        List<Integer> numbers1 = numbers.stream().collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println(numbers1);

        Set<Integer> treeNumbers = numbers.stream().collect(Collectors.toCollection(TreeSet::new));

        List<Artist> artists = Arrays.asList(
                new Artist("zdx", 1, true),
                new Artist("The beatles", 4, false));
        String combineNames = combineNames(artists);
        String combineNames1 = combineNames1(artists);
        String combineNames2 = combineNames2(artists);

        System.out.println(combineNames);
        System.out.println(combineNames1);
        System.out.println(combineNames2);

    }

    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        return artists.collect(maxBy(comparing(Artist::getMembers)));
    }

    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingDouble(x -> x.getTrackList().size()));
    }

    public static Map<Boolean, List<Artist>> bandOrSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }

    public static Map<Artist, List<Album>> albumByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician));
    }

    public static String combineNames(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .collect(joining(",", "[", "]"));
    }

    public static Map<Artist, Long> numberOfAlbums(List<Album> albums) {
        return albums.stream()
                .collect(groupingBy(Album::getMainMusician, counting()));
    }

    public static Map<Artist, List<String>> nameOfAlbums(List<Album> albums) {
        return albums.stream()
                .collect(groupingBy(Album::getMainMusician,
                        mapping(Album::getName, toList())));
    }

    public static String combineNames1(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombine(",", "[", "]"),
                        StringCombine::add,
                        StringCombine::megre)
                .toString();
    }

    public static String combineNames2(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .collect(new StringCollector(",", "[", "]"));
    }

    public static List<String> toUpperCase(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .collect(toList());
    }

    public static<T> int count(List<T> list) {
        return list.stream()
                .reduce(0, (i, t) -> i + 1, (j, n) -> j + n);
    }
}
