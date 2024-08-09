package strategy.creationStrategy;

import customClasses.Person;
import customClasses.factory.RandomFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonCreationStrategy implements CreationStrategy<Person> {

    @Override
    public List<Person> createMultiple(int count) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add((Person) RandomFactory.getFactory("person").createRandom());
        }
        return list;
    }
}
