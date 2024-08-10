package customClasses.comparators;

import customClasses.Animal;

import java.util.Comparator;

public class AnimalHairComparator implements Comparator<Animal> {
    // по наличию шерсти
    @Override
    public int compare(Animal o1, Animal o2) {
        return Boolean.compare(o1.isHair(), o2.isHair());
    }
}
