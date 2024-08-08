package customClasses.comparators;

import customClasses.Animal;

import java.util.Comparator;

public class AnimalEyeColorComparator implements Comparator<Animal> {
    // по глазам
    @Override
    public int compare(Animal o1, Animal o2) {
        return Integer.compare(o1.getEyeColor().ordinal(), o2.getEyeColor().ordinal());
    }
}
