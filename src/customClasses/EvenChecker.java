package customClasses;
@FunctionalInterface
public interface EvenChecker<T> { // для самостоятельной индикации, считает объект себя четным или нет
    boolean isEven();
}
