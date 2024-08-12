package enums;

public enum EyeColor {

    BLUE,
    RED,
    BLACK,
    YELLOW,
    GREEN,
    HETEROCHROMIC;

    public static void printColors() {
        for (var color : EyeColor.values()) {
            System.out.println(color.ordinal() + " -> " + color.name());
        }
    }
}
