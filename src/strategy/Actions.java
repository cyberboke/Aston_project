package strategy;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Actions {
    LOAD_RANDOM("load"),
    LOAD_FILE("load"),
    LOAD_CONSOLE("load");

    public final String name;
    Actions(String name){
        this.name = name;
    }
    public void addStrategy(BiConsumer<String, Function> strategy,
                            Function<Integer, List<?>> func) {
        strategy.accept(this.name, func);
    }
}
