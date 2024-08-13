package operation;

import java.util.*;

public class EvenOddSort {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(
                Arrays.asList(5,8,9,4,1,0,7,2,3,6)
        );
        System.out.println(list);
        sort(list, false);
        System.out.println(list);
    }

    public static <T extends Comparable<? super T>> void sort(List<Integer> list, boolean even) {
        evenSort(list,even, null);
    }

    public static <T> void sort(List<Integer> list, boolean even, Comparator<? super Integer> comparator) {
        evenSort(list, even, comparator);
    }
    private static <T> void evenSort(List<Integer> list, boolean even, Comparator<? super T> comparator) {
        Map<Integer, Integer> notSortEl = new LinkedHashMap<>();
        chooseElement(list, notSortEl, even);
        Collections.sort(list);
        notSortEl.forEach(list::add);
    }

    private static <T> void chooseElement(List<Integer> list, Map<Integer, Integer> map, boolean even){
        List<Integer> newList = new ArrayList<>();
        if(even){
            for (int i = 0; i < list.size(); i++){
                int item = (int) list.get(i);
                if (item % 2 == 0){
                    newList.add(item);
                }
                else{
                    map.put(i, item);
                }
            }
        }
        else {for (int i = 0; i < list.size(); i++){
            int item = (int) list.get(i);
            if (item % 2 != 0){
                newList.add(item);
            }
            else{
                map.put(i, item);
            }
        }
        }
        list.clear();
        list.addAll(newList);
    }
}
