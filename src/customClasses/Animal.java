package customClasses;

import enums.EyeColor;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;


public class Animal implements Comparable<Animal>, EvenChecker, Serializable {
    private final String kind;
    private final EyeColor eyeColor;
    private final boolean hair;


    private Animal(String string, EyeColor eyeColor, boolean hair) {
        this.kind = string;
        this.eyeColor = eyeColor;
        this.hair = hair;
    }

    public String getKind() {
        return kind;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public boolean isHair() {
        return hair;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Animal animal = (Animal) object;
        return hair == animal.hair && Objects.equals(kind, animal.kind) && eyeColor == animal.eyeColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, eyeColor, hair);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "kind =\t'" + kind + '\'' + '\t' +
                ", hair =\t" + (hair ? "with hair   " : "without hair") + '\t' +
                ", eyeColor =\t" + eyeColor +
                '}';
    }

    // по виду
    @Override
    public int compareTo(Animal o) {
        return Comparator
                .comparing(Animal::getKind)
                .thenComparing(Animal::isHair)
                .thenComparing(Animal::getEyeColor).compare(this, o);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isEven() { // по шерсти
        return hair;
    }

    public static class Builder {
        private String kind;
        private EyeColor eyeColor;
        private boolean hair;

        public Builder kind(String kind) {
            this.kind = kind;
            return this;
        }

        public Builder hair(boolean hair) {
            this.hair = hair;
            return this;
        }

        public Builder eyeColor(EyeColor eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Animal build() {
            return new Animal(kind, eyeColor, hair);
        }
    }
}
