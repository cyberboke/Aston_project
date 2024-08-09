package customClasses.factory.loader;
import customClasses.enums.*;

public class LoaderFactory {
    public static Loader<?> getFactory(TypeLoad loader) {
        return switch (loader) {
            case LOAD_RANDOM -> new RandomLoader<>();
            case LOAD_FILE -> new FileLoader<>();
            case LOAD_CONSOLE -> new ConsoleLoader<>();
            default -> throw new IllegalArgumentException("Unknown type recourse: " + loader);
        };
    }
}
