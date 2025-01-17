package customClasses.comparators;

import customClasses.Person;

import java.util.Comparator;

public class PersonGenderComparator implements Comparator<Person> {

    @Override
    public String toString() {
        return "PersonGenderComparator";
    }

    @Override
    public int compare(Person o1, Person o2) {
        return Boolean.compare(o1.getGender(), o2.getGender());
    }
}
