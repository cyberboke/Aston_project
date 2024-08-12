package customClasses.factory.loader;

import enums.TypeClass;

import java.util.List;

public interface Loader<T> {
    List<T> load(TypeClass type, int count);
}
