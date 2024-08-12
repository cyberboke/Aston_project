package customClasses.comparators;

import customClasses.Barrel;

import java.util.Comparator;

public class BarrelMaterialComparator implements Comparator<Barrel> {

    @Override
    public String toString() {
        return "BarrelMaterialComparator";
    }

    @Override
    public int compare(Barrel o1, Barrel o2) {
        return o1.getMaterial().compareTo(o2.getMaterial());
    }
}
