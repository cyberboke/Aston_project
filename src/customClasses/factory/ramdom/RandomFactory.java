package customClasses.factory.ramdom;

import enums.TypeClass;

public class RandomFactory {

    private RandomFactory() {}

    public static RandomCreatable<?> getFactory(TypeClass type) {
        return switch (type) {
            case ANIMAL -> new AnimalFactory();
            case PERSON -> new PersonFactory();
            case BARREL -> new BarrelFactory();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
