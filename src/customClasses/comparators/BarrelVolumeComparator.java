package customClasses.comparators;

import customClasses.Barrel;

import java.util.Comparator;

public class BarrelVolumeComparator implements Comparator<Barrel> {

    @Override
    public String toString() {
        return "BarrelVolumeComparator";
    }

    @Override
    public int compare(Barrel o1, Barrel o2) {
        return Integer.compare(o1.getVolume(), o2.getVolume());
    }
}
