package common;

public class Artist {
    private String name;

    private int members;

    private boolean isSolo;

    public Artist(String name, int members, boolean isSolo) {
        this.name = name;
        this.members = members;
        this.isSolo = isSolo;
    }

    public String getName() {
        return name;
    }

    public int getMembers() {
        return members;
    }

    public boolean isSolo() {
        return isSolo;
    }
}
