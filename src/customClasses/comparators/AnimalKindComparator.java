package customClasses.comparators;

import customClasses.Animal;

import java.util.Comparator;

public class AnimalKindComparator implements Comparator<Animal> {

    @Override
    public String toString() {
        return "AnimalKindComparator";
    }

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getKind().compareTo(o2.getKind());
    }
}
