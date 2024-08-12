package customClasses.comparators;

import customClasses.Person;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person> {

    @Override
    public String toString() {
        return "PersonAgeComparator";
    }

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
