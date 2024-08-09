package strategy.creationStrategy;

import java.util.List;

public class ContextCreationStrategy<T> {
    private final CreationStrategy<T> strategy;

    public ContextCreationStrategy(CreationStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public List<T> executeStrategy(int count) {
        return strategy.createMultiple(count);
    }
}
