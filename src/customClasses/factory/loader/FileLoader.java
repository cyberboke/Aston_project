package customClasses.factory.loader;

import enums.TypeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileLoader<T> implements Loader<T>{
    @Override
    public List<T> load(TypeClass type, int count) {
        List<T> list = new ArrayList<>();
        try(FileInputStream fileInputStream= new FileInputStream(type.filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            list = (List<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("For "+type.name()+" of file reading is not available");
        }
        try {
            list = list.subList(0, count);
        }
        catch (IndexOutOfBoundsException ignore){}
        return list;
    }
}
