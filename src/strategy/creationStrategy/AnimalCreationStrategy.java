package strategy.creationStrategy;

import customClasses.Animal;
import customClasses.factory.RandomFactory;

import java.util.ArrayList;
import java.util.List;

public class AnimalCreationStrategy implements CreationStrategy<Animal> {

    @Override
    public List<Animal> createMultiple(int count) {
        List<Animal> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add((Animal) RandomFactory.getFactory("animal").createRandom());
        }
        return list;
    }
}
