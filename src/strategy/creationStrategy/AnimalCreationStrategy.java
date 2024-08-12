package strategy.creationStrategy;

import customClasses.Animal;
import enums.EyeColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalCreationStrategy implements CreationStrategy<Animal> {
    private static final Random rnd = new Random();
    private static final String[] kinds = {"Cat", "Dog", "Roach", "Fish", "Bug", "Lion", "Bear", "Wolf"};

    @Override
    public List<Animal> create(int count) {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            animals.add(Animal.builder()
                    .kind(kinds[rnd.nextInt(kinds.length)])
                    .hair(rnd.nextBoolean())
                    .eyeColor(EyeColor.values()[rnd.nextInt(EyeColor.values().length)])
                    .build());
        }
        return animals;
    }
}
