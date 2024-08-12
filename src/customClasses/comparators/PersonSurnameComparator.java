package customClasses.comparators;

import customClasses.Person;

import java.util.Comparator;

public class PersonSurnameComparator implements Comparator<Person> {

    @Override
    public String toString() {
        return "PersonSurnameComparator";
    }

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
}
