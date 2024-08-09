package strategy.creationStrategy;

import java.util.List;

public interface CreationStrategy<T> {
    List<T> create(int count);  // Метод для создания списка объектов
}
