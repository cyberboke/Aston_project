package customClasses.enums;

public enum Classes {
    ANIMAL("ANIMAL_file.txt"),
    PERSON("PEOPLE_file.txt"),
    BARREL("BARREL_file.txt");
    public final String filePath;

    Classes(String file) {
        this.filePath = file;
    }

}
