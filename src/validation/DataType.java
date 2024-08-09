package validation;

public enum DataType {

    NAME("^[A-Z][a-z]{1,20}$"),
    WORD("^[a-z]{1,20}$"),
    NUMBER("^[0-9]{1,3}$");

    private final String pattern;

    DataType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
