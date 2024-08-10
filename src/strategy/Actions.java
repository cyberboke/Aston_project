package strategy;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public enum Actions {
    LOAD("load"),
    SORT("sort"),
    SEARCH("search");


    public final String name;
    Actions(String name){
        this.name = name;
    }
    public void addStrategy(BiConsumer<String, Runnable> strategy,
                            Runnable func) {
        strategy.accept(this.name, func);
    }
}
