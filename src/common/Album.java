package common;

import java.util.List;

public class Album {

    private List<String> trackList;

    private Artist MainMusician;

    private String name;

    public List<String> getTrackList() {
        return trackList;
    }

    public Artist getMainMusician() {
        return MainMusician;
    }

    public String getName() {
        return name;
    }
}
