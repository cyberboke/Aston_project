package operation;

public class BinarySearch {
    <T extends Comparable<T>> int binarySearch(T[] array, T element) {
        // вначале левая и правая границы равны первому и последнему элементу массива
        var left = 0;
        var right = array.length - 1;
        // пока левая и правая границы поиска не пересеклись
        while (left <= right) {
            // индекс текущего элемента находится посередине
            var middle = left + (right - left) / 2;
            var current = array[middle];
            //сравниваем элементы
            var compareResult = current.compareTo(element);
            if (compareResult == 0) {
                return middle;
            } else if (compareResult < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }
}
