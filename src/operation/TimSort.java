package operation;

import java.util.Comparator;
import java.util.List;

public class TimSort {

    private static final int MIN_RUN = 32;

    // Метод для сортировки списка объектов, которые реализуют интерфейс Comparable
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        timsort(list, null);
    }

    // Метод для сортировки списка объектов с использованием Comparator
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        timsort(list, comparator);
    }

    // Основной метод, реализующий алгоритм Timsort
    private static <T> void timsort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        T[] arr = (T[]) list.toArray();

        // Шаг 1: Разбить массив на руны и отсортировать каждый с помощью Insertion Sort
        for (int i = 0; i < n; i += MIN_RUN) {
            insertionSort(arr, i, Math.min(i + MIN_RUN - 1, n - 1), comparator);
        }

        // Шаг 2: Слияние отсортированных рунов
        for (int size = MIN_RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(arr, left, mid, right, comparator);
                }
            }
        }
        // Перенос отсортированного массива обратно в список
        for (int i = 0; i < n; i++) {
            list.set(i, arr[i]);
        }
    }
    // Реализация Insertion Sort
    private static <T> void insertionSort(T[] arr, int left, int right, Comparator<? super T> comparator) {
        for (int i = left + 1; i <= right; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= left && compare(arr[j], key, comparator) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Реализация слияния двух отсортированных подмассивов
    private static <T> void merge(T[] arr, int left, int mid, int right, Comparator<? super T> comparator) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        T[] leftArr = (T[]) new Object[len1];
        T[] rightArr = (T[]) new Object[len2];

        System.arraycopy(arr, left, leftArr, 0, len1);
        System.arraycopy(arr, mid + 1, rightArr, 0, len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (compare(leftArr[i], rightArr[j], comparator) <= 0) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < len1) {
            arr[k++] = leftArr[i++];
        }
        while (j < len2) {
            arr[k++] = rightArr[j++];
        }
    }

    // Вспомогательный метод для сравнения двух элементов
    private static <T> int compare(T a, T b, Comparator<? super T> comparator) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return ((Comparable<? super T>) a).compareTo(b);
        }
    }
}
