package validation;

public enum DataType {

    NAME("^[A-ZА-Я][a-zа-я]*(?:(\\-|\\s)[A-ZА-Я][a-zа-я]*)?$"), // можно Имя, а можно Имя-Фамилия а можно Имя Фамилия
    WORD("^[a-z]{1,20}$"), // любое слово не более 20 символов
    NUMBER("^[0-9]{1,3}$"), // любое трехзначное положительное число
    BOOL("^(true|false)"); // true или false

    private final String pattern;

    DataType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
