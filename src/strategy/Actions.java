package strategy;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Actions {
    LOAD("load"),
    SORT("sort"),
    SEARCH("search");


    public final String name;
    Actions(String name){
        this.name = name;
    }
    public void addStrategy(BiConsumer<String, Function> strategy,
                            Function<Integer, List<?>> func) {
        strategy.accept(this.name, func);
    }
}
