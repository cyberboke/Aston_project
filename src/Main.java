import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.enums.Classes;
import customClasses.factory.RandomCreatable;
import customClasses.factory.RandomFactory;
import customClasses.factory.loader.LoaderFactory;
import strategy.Actions;
import strategy.creationStrategy.ContextCreationStrategy;
import validation.DataType;
import validation.Validator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static List<Object> myList = new ArrayList<>();

    public static void main(String[] args) {

        /*
        реализация факрики через стратегию
         */
        Classes type = Classes.PERSON; // иммитация выбора пользователя
        ListClasses<?> list = ListClasses.generateList(type);
        TypeLoad typeLoad = TypeLoad.LOAD_RANDOM; // иммитация выбора источника данных
        Actions.LOAD.addStrategy(list.load::add, (count)->
                LoaderFactory.getFactory(typeLoad).load(type, count)
        );
        try {
            list.executeAll(10);
        }
        catch (IllegalArgumentException e){
            System.err.println(e);
        }
        list.list.forEach(System.out::println);

        System.out.println("================File===============");

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
/*-----------------------------------------------------------------*/
        // Тест на стратегию по добавлению объектов заполненных рандомно
        while (true) {
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
        }
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
