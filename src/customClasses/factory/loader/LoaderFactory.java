package customClasses.factory.loader;

import customClasses.enums.TypeLoad;
import strategy.Actions;

public class LoaderFactory {
    public static Loader<?> getFactory(TypeLoad loader) {
        return switch (loader) {
            case LOAD_RANDOM -> new RandomLoader<>();
            case LOAD_FILE -> new FileLoader<>();
            //case BARREL -> new BarrelFactory();
            default -> throw new IllegalArgumentException("Unknown type recourse: " + loader);
        };
    }
}
