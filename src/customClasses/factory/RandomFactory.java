package customClasses.factory;

import customClasses.enums.Classes;

public class RandomFactory {

    private RandomFactory() {}

    public static RandomCreatable<?> getFactory(Classes type) {
        return switch (type) {
            case ANIMAL -> new AnimalFactory();
            case PERSON -> new PersonFactory();
            case BARREL -> new BarrelFactory();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
