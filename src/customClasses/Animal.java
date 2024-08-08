package customClasses;

import customClasses.enums.EyeColor;

import java.io.Serializable;
import java.util.Objects;


public class Animal implements Serializable {
    private final String string;
    private final EyeColor eyeColor;
    private final boolean hair;

    private Animal(String string, EyeColor eyeColor, boolean hair) {
        this.string = string;
        this.eyeColor = eyeColor;
        this.hair = hair;
    }

    public String getKind() {
        return string;
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
        return hair == animal.hair && Objects.equals(string, animal.string) && eyeColor == animal.eyeColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, eyeColor, hair);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "string='" + string + '\'' +
                ", eyeColor=" + eyeColor +
                ", hair=" + hair +
                '}';
    }

    public static Builder builder() {
        return new Builder();
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
