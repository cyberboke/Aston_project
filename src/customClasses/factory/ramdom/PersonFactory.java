package customClasses.factory.ramdom;

import customClasses.Person;

import java.util.Random;

public class PersonFactory implements RandomCreatable<Person> {

    private static final Random rnd = new Random();
    private static final String[] SURNAMES = {"Adamson", "Black", "Carrington", "Chapman", "Davidson", "Farrell", "Garrison", "Goldman", "James", "Lewin", "Taylor"};

    @Override
    public Person createRandom() {
        return Person.builder()
                .surname(SURNAMES[rnd.nextInt(SURNAMES.length)])
                .gender(rnd.nextBoolean())
                .age(rnd.nextInt(120))
                .build();
    }
}
