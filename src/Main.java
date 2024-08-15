
import enums.TypeClass;
import enums.*;
import customClasses.factory.loader.LoaderFactory;
import menu.Menu;
import operation.BinarySearch;
import operation.EvenOddSort;
import operation.TimSort;
import strategy.Actions;
import java.util.*;


public class Main {
    private static ListClasses listClasses;

    private static TypeClass typeClass;
    private static TypeAction typeAction;
    private static int count = 10;
    private static TypeSort typeSort;
    private static TypeChoice typeChoice;

    public static void main(String[] args) {
        do {
            typeClass = Menu.choosingClass();
            listClasses = ListClasses.generateList(typeClass);

            do {
                TypeLoad typeLoad2 = Menu.choosingLoad();
                count = Menu.choosingSize();
                Actions.LOAD.addStrategy(listClasses.strategy::add, () -> {
                    listClasses.list.clear();
                    listClasses.list.addAll(
                            LoaderFactory.getFactory(typeLoad2).load(typeClass, count)
                    );
                });
                listClasses.execute(Actions.LOAD.name);
                Actions.LOAD.removeStrategy(listClasses.strategy::remove);

                do {
                    typeAction = Menu.choosingAction();
                    if (typeAction == TypeAction.SEARCH) {
                        Actions.SORT.addStrategy(listClasses.strategy::add, () -> {
                            TimSort.sort(listClasses.list);
                        });
                        Actions.SEARCH.addStrategy(listClasses.strategy::add, () -> {
                            Object obj = Menu.createObject(typeClass);
                            int index = BinarySearch.binarySearch(listClasses.list, (Comparable) obj);
                            System.out.println("Object: " + obj);
                            if (index == -1) {
                                System.out.println("Not found"+"\n");
                            } else {
                                System.out.println("Found"+"\n");
                            }
                        });
                    } else {
                        typeSort = Menu.choosingSort();
                        Actions.SORT.addStrategy(listClasses.strategy::add, () -> {
                            Comparator comp = Menu.choosingComparator(typeClass);
                            switch (typeSort) {
                                case TIMSORT -> TimSort.sort(listClasses.list, comp);
                                case EVENSORT -> EvenOddSort.sort(listClasses.list, true, comp);
                                case ODDSORT -> EvenOddSort.sort(listClasses.list, false, comp);
                            }
                            listClasses.list.forEach(System.out::println);
                            System.out.println();
                        });
                    }
                    try {
                        listClasses.executeAll();
                    } catch (RuntimeException e) {
                        System.err.println("An error has occurred. Please try again.");
                    }

                    typeChoice = Menu.choosingChoice();
                    if (typeChoice == TypeChoice.EXIT) return;
                    Actions.SORT.removeStrategy(listClasses.strategy::remove);
                    Actions.SEARCH.removeStrategy(listClasses.strategy::remove);
                } while (typeChoice == TypeChoice.TYPE_ACTION);
            } while (typeChoice == TypeChoice.TYPE_LOAD);
        } while (typeChoice == TypeChoice.TYPE_CLASS);
    }

}
