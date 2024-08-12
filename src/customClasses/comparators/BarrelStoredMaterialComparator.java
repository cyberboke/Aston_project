package customClasses.comparators;

import customClasses.Barrel;

import java.util.Comparator;

public class BarrelStoredMaterialComparator implements Comparator<Barrel> {

    @Override
    public String toString() {
        return "BarrelStoredMaterialComparator";
    }

    @Override
    public int compare(Barrel o1, Barrel o2) {
        return o1.getStoredMaterial().compareTo(o2.getStoredMaterial());
    }
}
