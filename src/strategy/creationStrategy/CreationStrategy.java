package strategy.creationStrategy;

import strategy.Strategy;

import java.util.List;

public interface CreationStrategy<T> extends Strategy {
    List<T> createMultiple(int count);
}
