package operation;

import customClasses.EvenChecker;
import customClasses.factory.loader.LoaderFactory;
import enums.TypeClass;
import enums.TypeLoad;

import java.util.*;

public class EvenOddSort {

    public static <T extends EvenChecker<? super T>> void sort(List<T> list, boolean even) {
        evenSort(list,even, null);
    }

    public static <T> void sort(List<T> list, boolean even, Comparator<? super T> comparator) {
        evenSort(list, even, comparator);
    }
    private static <T> void evenSort(List<T> list, boolean even, Comparator<? super T> comparator) {
        Map<Integer, T> notSortEl = new LinkedHashMap<>();
        chooseElement(list, notSortEl, even);
        Collections.sort(list, comparator);
        notSortEl.forEach(list::add);
    }

    private static <T> void chooseElement(List<T> list, Map<Integer, T> map, boolean even){
        List<T> newList = new ArrayList<>();
        if(even){
            for (int i = 0; i < list.size(); i++){
                T item = list.get(i);
                if (((EvenChecker<?>)item).isEven())
                    newList.add(item);
                else
                    map.put(i, item);
            }
        }
        else {
            for (int i = 0; i < list.size(); i++){
                T item = list.get(i);
                if (!((EvenChecker<?>)item).isEven())
                    newList.add(item);
                else
                    map.put(i, item);
            }
        }
        list.clear();
        list.addAll(newList);
    }
}
