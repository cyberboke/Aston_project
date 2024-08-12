package customClasses.comparators.registry;

import customClasses.comparators.*;
import enums.TypeClass;

import java.util.*;

public class ComparatorRegistry {

    private static final Map<TypeClass, List<Comparator<?>>> comparators = new HashMap<>();

    private ComparatorRegistry() {}

    static {
        registerComparators();
    }

    private static void registerComparators() {
        comparators.put(TypeClass.ANIMAL, Arrays.asList(
                new AnimalKindComparator(),
                new AnimalEyeColorComparator(),
                new AnimalHairComparator()
        ));
        comparators.put(TypeClass.PERSON, Arrays.asList(
                new PersonAgeComparator(),
                new PersonSurnameComparator(),
                new PersonGenderComparator()
        ));
        comparators.put(TypeClass.BARREL, Arrays.asList(
                new BarrelStoredMaterialComparator(),
                new BarrelMaterialComparator(),
                new BarrelVolumeComparator()
        ));
    }

    public static List<Comparator<?>> getComparators(TypeClass type) {
        return comparators.getOrDefault(type, Collections.emptyList());
    }
}
