package customClasses.factory.loader;

import customClasses.enums.Classes;

import java.util.List;

public interface Loader<T> {
    List<T> load(Classes type, int count);
}
