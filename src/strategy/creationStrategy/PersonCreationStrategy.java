package strategy.creationStrategy;

import customClasses.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonCreationStrategy implements CreationStrategy<Person> {
    private static final Random rnd = new Random();
    private static final String[] surnames = {"Adamson", "Black", "Carrington", "Chapman", "Davidson", "Farrell", "Garrison", "Goldman", "James", "Lewin", "Taylor"};

    @Override
    public List<Person> create(int count) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            persons.add(Person.builder()
                    .surname(surnames[rnd.nextInt(surnames.length)])
                    .gender(rnd.nextBoolean())
                    .age(rnd.nextInt(120))
                    .build());
        }
        return persons;
    }
}
