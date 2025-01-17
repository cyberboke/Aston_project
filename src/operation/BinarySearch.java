package operation;

import java.util.List;

public class BinarySearch {
    public static <T extends Comparable<T>> int binarySearch(List<T> array, T element) {
        // вначале левая и правая границы равны первому и последнему элементу массива
        var left = 0;
        var right = array.size() - 1;
        // пока левая и правая границы поиска не пересеклись
        while (left <= right) {
            // индекс текущего элемента находится посередине
            var middle = left + (right - left) / 2;
            var current = array.get(middle);
            //сравниваем элементы
            var compareResult = current.compareTo(element);
            // нашли элемент - возвращаем его индекс
            if (compareResult == 0) {
                return middle;
            // текущий элемент меньше искомого - сдвигаем левую границу
            } else if (compareResult < 0) {
                left = middle + 1;
            // иначе сдвигаем правую границу
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }
}
