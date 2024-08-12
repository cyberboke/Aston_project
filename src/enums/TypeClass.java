package enums;

public enum TypeClass {
    ANIMAL("ANIMAL_file.txt"),
    PERSON("PEOPLE_file.txt"),
    BARREL("BARREL_file.txt");
    public final String filePath;

    TypeClass(String file) {
        this.filePath = file;
    }

}
