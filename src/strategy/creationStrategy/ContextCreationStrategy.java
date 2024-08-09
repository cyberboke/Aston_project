package strategy;

import strategy.creationStrategy.AnimalCreationStrategy;
import strategy.creationStrategy.BarrelCreationStrategy;
import strategy.creationStrategy.CreationStrategy;
import strategy.creationStrategy.PersonCreationStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextCreationStrategy<T> {
    private final CreationStrategy<T> strategy;

    // Карта для хранения всех доступных стратегий
    private static final Map<String, CreationStrategy<?>> strategyMap = new HashMap<>();

    static {
        // Заполнение карты стратегий
        strategyMap.put("person", new PersonCreationStrategy());
        strategyMap.put("animal", new AnimalCreationStrategy());
        strategyMap.put("barrel", new BarrelCreationStrategy());
    }

    // Конструктор контекста, принимает стратегию
    public ContextCreationStrategy(CreationStrategy<T> strategy) {
        this.strategy = strategy;
    }

    // Метод для получения контекста стратегии по типу
    @SuppressWarnings("unchecked")
    public static <T> ContextCreationStrategy<T> getContext(String type) {
        CreationStrategy<?> strategy = strategyMap.get(type);
        return strategy != null ? new ContextCreationStrategy<>((CreationStrategy<T>) strategy) : null;
    }

    // Выполнение стратегии для создания объектов
    public List<T> executeStrategy(int count) {
        return strategy.create(count);
    }
}