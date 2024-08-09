package customClasses.factory.loader;

import customClasses.Animal;
import customClasses.enums.Classes;
import customClasses.factory.AnimalFactory;
import customClasses.factory.BarrelFactory;
import customClasses.factory.PersonFactory;
import customClasses.factory.RandomCreatable;
import strategy.Actions;

public class LoaderFactory {
    public static Loader<?> getFactory(Actions loader) {
        return switch (loader) {
            case LOAD_RANDOM -> new RandomLoader<>();
            case LOAD_FILE -> new FileLoader<>();
            //case BARREL -> new BarrelFactory();
            default -> throw new IllegalArgumentException("Unknown type recourse: " + loader);
        };
    }
}
