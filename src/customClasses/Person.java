package customClasses;

import customClasses.comparators.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private final boolean gender;
    private final int age;
    private final String surname;

    private static final Comparator<Person> surnameComparator = new PersonSurnameComparator();
    private static final Comparator<Person> ageComparator = new PersonAgeComparator();
    private static final Comparator<Person> genderComparator = new PersonGenderComparator();

    private Person(boolean gender, int age, String surname) {
        this.gender = gender;
        this.age = age;
        this.surname = surname;
    }

    public boolean getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return gender == person.gender && age == person.age && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, age, surname);
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender=" + (gender ? "man" : "woman") +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    // по фамилии
    @Override
    public int compareTo(Person o) {
        return surnameComparator.thenComparing(ageComparator).thenComparing(genderComparator).compare(this, o);
    }

    public static class Builder {
        private boolean gender;
        private int age;
        private String surname;

        public Builder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Person build() {
            return new Person(gender, age, surname);
        }
    }
}
