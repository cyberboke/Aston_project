package customClasses.factory;

public class RandomFactory {

    private RandomFactory() {}

    public static RandomCreatable<?> getFactory(String type) {
        return switch (type.toLowerCase()) {
            case "animal" -> new AnimalFactory();
            case "person" -> new PersonFactory();
            case "barrel" -> new BarrelFactory();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
