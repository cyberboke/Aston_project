package enums;

public enum TypeClass {
    ANIMAL("res//ANIMAL_file.ser"),
    PERSON("res//PEOPLE_file.ser"),
    BARREL("res//BARREL_file.ser");
    public final String filePath;

    TypeClass(String file) {
        this.filePath = file;
    }

}
