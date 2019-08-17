package chap5;

public class StringCombine {

    private String delimited;

    private String prefix;

    private String suffix;

    private StringBuilder builder;

    public StringCombine(String delimited, String prefix, String suffix) {
        this.delimited = delimited;
        this.prefix = prefix;
        this.suffix = suffix;
        this.builder = new StringBuilder();
    }

    public StringCombine add(String elem) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(delimited);
        }
        builder.append(elem);
        return this;
    }

    public StringCombine megre(StringCombine other) {
        builder.append(other.builder);
        return this;
    }

    public String toString() {
        return builder.append(suffix).toString();
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }
}
