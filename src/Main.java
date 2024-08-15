
import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
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
    private static int count = 10;

    public static void main(String[] args) {

        /*
        TypeClass typeClass = Menu.choosingClass();
        int size = Menu.choosingSize();
        TypeLoad typeLoad = Menu.choosingLoad();
        TypeAction typeAction = Menu.choosingAction();
        Comparator comparator = Menu.choosingComparator(typeClass);

        if (typeAction == TypeAction.SORT) {
            TypeSort typeSort = Menu.choosingSort();
        } else {
            switch (typeClass) {
                case ANIMAL: Animal animal = Menu.fillAnimal(comparator); break;
                case PERSON: Person person = Menu.fillPerson(comparator); break;
                case BARREL: Barrel barrel = Menu.fillBarrel(comparator); break;
            }
        }

        TypeChoice typeChoice = Menu.choosingChoice();
        */


        TypeClass type2 = TypeClass.ANIMAL; // иммитация выбора пользователя
        listClasses = ListClasses.generateList(type2);

        TypeLoad typeLoad2 = TypeLoad.LOAD_FILE; // иммитация выбора источника данных
        count = 5;                                 // иммитация выбора количества объектов в массиве
        Actions.LOAD.addStrategy(listClasses.strategy::add, ()-> {
            listClasses.list.clear();
            listClasses.list.addAll(
                    LoaderFactory.getFactory(typeLoad2).load(type2, count)
            );
        });

        TypeAction typeAction = TypeAction.SEARCH; // иммитация выбора
        if(typeAction == TypeAction.SEARCH){
            Object obj = Animal.builder(). // иммитация запроса объекта на поиск
                    hair(false).
                    kind("Cat").
                    eyeColor(EyeColor.BLUE).
                    build();
            Actions.SORT.addStrategy(listClasses.strategy::add, ()->{
                TimSort.sort(listClasses.list);
            });
            Actions.SEARCH.addStrategy(listClasses.strategy::add, ()->{
                int index = BinarySearch.binarySearch(listClasses.list, (Comparable) obj);
                System.out.println("Fount " + index);
            });
        }
        else {
            TypeSort typeSort = TypeSort.TIMSORT; // иммитация выбора сортировки
            Actions.SORT.addStrategy(listClasses.strategy::add, ()->{
                Comparator comp = null;         // иммитация выбора поля сортировки
                switch (typeSort){
                    case TIMSORT -> TimSort.sort(listClasses.list, comp);
                    case EVENSORT -> EvenOddSort.sort(listClasses.list, true, comp);
                    case ODDSORT -> EvenOddSort.sort(listClasses.list, false, comp);
                }
            });
        }

        try {
            listClasses.executeAll();
        }
        catch (RuntimeException e){
            System.err.println(e);
        }

        Actions.LOAD.removeStrategy(listClasses.strategy::remove); // Удаление действия из стратегии

        listClasses.list.forEach(System.out::println);

    }

}
