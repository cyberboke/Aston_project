
import customClasses.Barrel;
import customClasses.comparators.BarrelStoredMaterialComparator;
import customClasses.enums.Classes;
import customClasses.enums.*;
import customClasses.factory.loader.LoaderFactory;
import operation.TimSort;
import strategy.Actions;
import strategy.creationStrategy.ContextCreationStrategy;
import validation.DataType;
import validation.Validator;

import java.util.*;


public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static List<Object> myList = new ArrayList<>();

    private static ListClasses listClasses;
    private static int count = 10;

    public static void main(String[] args) {

        /*
        реализация факрики через стратегию
         */
      /*  Classes type = Classes.PERSON; // иммитация выбора пользователя
        ListClasses<?> list = ListClasses.generateList(type);
        TypeLoad typeLoad = TypeLoad.LOAD_RANDOM; // иммитация выбора источника данных
        Actions.LOAD.addStrategy(list.strategy::add, (count)->
                LoaderFactory.getFactory(typeLoad).load(type, count)
        );
        try {
            list.executeAll(10);
        }
        catch (IllegalArgumentException e){
            System.err.println(e);
        }
        list.list.forEach(System.out::println);
*/
/*        System.out.println("================File===============");

        Classes type1 = Classes.ANIMAL; // иммитация выбора пользователя
        ListClasses<?> list1 = ListClasses.generateList(type1);
        TypeLoad typeLoad1 = TypeLoad.LOAD_FILE; // иммитация выбора источника данных
        Actions.LOAD.addStrategy(list1.load::add, (count)->
                LoaderFactory.getFactory(typeLoad1).load(type1, count)
        );
        try {
            list1.executeAll(15);
        }
        catch (RuntimeException e){
            System.err.println(e);
        }
        list1.list.forEach(System.out::println);
*/
              /*
        консоль
         */
    /*    System.out.println();
        System.out.println("===============STRATEGY================");
        System.out.println("================Console===============");
        Classes typeAnimal = Classes.ANIMAL; // иммитация выбора пользователя
        ListClasses<?> listConsole = ListClasses.generateList(typeAnimal);
        TypeLoad typeLoadConsole = TypeLoad.LOAD_CONSOLE;  // иммитация выбора источника данных
        Actions.LOAD.addStrategy(listConsole.load::add, (count)->
                LoaderFactory.getFactory(typeLoadConsole).load(typeAnimal, count)
        );
        try {
            listConsole.executeAll(10);
        } catch (IllegalArgumentException e){
            System.err.println(e);
        }
        listConsole.list.forEach(System.out::println);
*/

        System.out.println("================TimSort===============");

        Classes type2 = Classes.BARREL; // иммитация выбора пользователя
        listClasses = ListClasses.generateList(type2);

        TypeLoad typeLoad2 = TypeLoad.LOAD_CONSOLE; // иммитация выбора источника данных
        count = 1;                                 // иммитация выбора количества объектов в массиве
        Actions.LOAD.addStrategy(listClasses.strategy::add, ()-> {
            listClasses.list.clear();
            listClasses.list.addAll(
                    LoaderFactory.getFactory(typeLoad2).load(type2, count)
            );
        });

        TypeSort typeSort = TypeSort.TIMSORT; // иммитация выбора сортировки
        Actions.SORT.addStrategy(listClasses.strategy::add, ()->{
            TimSort.sort(listClasses.list);
        });
        Actions.SEARCH.addStrategy(listClasses.strategy::add, ()->{

        });

        try {
            listClasses.executeAll();
        }
        catch (RuntimeException e){
            System.err.println(e);
        }
        listClasses.list.forEach(System.out::println);
        System.out.println(listClasses.list.size());

       /* System.out.println("---------------");
        List <Integer> l = new ArrayList<>();
        l.add(8);
        l.add(1);
        l.add(5);
        TimSort.sort(l, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        l.forEach(System.out::println);*/
        /*System.out.println("---------------");
        //Collections.sort(listClasses.list);
        //TimSort.sort(listClasses.list);
        //TimSort.sort(listClasses.list, (Comparator<Comparable>) (o1, o2) -> ((Barrel)o1).getMaterial().compareTo(((Barrel)o2).getMaterial()));
        TimSort.sort(listClasses.list, new BarrelStoredMaterialComparator());
        listClasses.list.forEach(System.out::println);*/
/*-----------------------------------------------------------------*/
        // Тест на стратегию по добавлению объектов заполненных рандомно
       /* while (true) {
            printMenu();
            String command = sc.nextLine().toLowerCase();

            switch (command) {
                case "exit" -> {
                    sc.close();
                    return;
                }
                case "print" -> {
                    if (myList == null || myList.isEmpty()) {
                        System.out.println("Список пуст.");
                    } else {
                        myList.forEach(System.out::println);
                    }
                }
                default -> {
                    ContextCreationStrategy<?> context = ContextCreationStrategy.getContext(command);
                    if (context != null) {
                        System.out.println("Сколько объектов ты хочешь добавить в список?");
                        int count = number(sc.nextLine());
                        myList.addAll(context.executeStrategy(count));
                        System.out.println("Добавлено " + count + " объектов.");
                    } else {
                        System.out.println("Неизвестная команда");
                    }
                }
            }
        }*/
    }

    private static int number(String st) {
        if (Validator.isValidData(st, DataType.NUMBER)) {
            return Integer.parseInt(st);
        } else {
            System.out.println("Введите целое положительное число!");
            return number(sc.nextLine());
        }
    }

    private static void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("person - Работа с Person");
        System.out.println("animal - Работа с Animal");
        System.out.println("barrel - Работа с Barrel");
        System.out.println("print - Показать текущий список");
        System.out.println("exit - Выйти из программы");
    }
}
