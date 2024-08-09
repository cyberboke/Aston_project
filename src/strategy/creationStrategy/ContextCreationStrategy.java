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

    public static ContextCreationStrategy<?> getContext(String type) {
        return switch (type) {
            case "person" -> new ContextCreationStrategy<>(new PersonCreationStrategy());
            case "animal" -> new ContextCreationStrategy<>(new AnimalCreationStrategy());
            case "barrel" -> new ContextCreationStrategy<>(new BarrelCreationStrategy());
            default -> null;
        };
    }
}
