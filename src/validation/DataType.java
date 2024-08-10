package validation;

public enum DataType {

    NAME("^[A-ZА-Я][a-zа-яA-ZА-Я]*(?:\\s[A-ZА-Я][a-zа-яA-ZА-Я]*)?$"),
    WORD("^[a-z]{1,20}$"),
    NUMBER("^[0-9]{1,3}$"),
    BOOL("^(true|false)");

    private final String pattern;

    DataType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
