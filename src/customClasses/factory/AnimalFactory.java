package customClasses.factory;

import customClasses.Animal;
import customClasses.enums.EyeColor;

import java.util.Random;

public class AnimalFactory implements RandomCreatable<Animal> {

    private static final Random rnd = new Random();
    private static final String[] KINDS = {"Cat", "Dog", "Roach", "Fish", "Bug", "Lion", "Bear", "Wolf"};

    @Override
    public Animal createRandom() {
        return Animal.builder()
                .kind(KINDS[rnd.nextInt(KINDS.length)])
                .hair(rnd.nextBoolean())
                .eyeColor(EyeColor.values()[rnd.nextInt(EyeColor.values().length)])
                .build();
    }
}

