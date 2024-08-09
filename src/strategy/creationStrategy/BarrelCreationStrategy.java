package strategy.creationStrategy;

import customClasses.Barrel;
import customClasses.factory.RandomFactory;

import java.util.ArrayList;
import java.util.List;

public class BarrelCreationStrategy implements CreationStrategy<Barrel> {

    @Override
    public List<Barrel> createMultiple(int count) {
        List<Barrel> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add((Barrel) RandomFactory.getFactory("barrel").createRandom());
        }
        return list;
    }
}
