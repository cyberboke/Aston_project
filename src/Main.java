import strategy.creationStrategy.ContextCreationStrategy;

import validation.DataType;
import validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            showMainMenu();  // Показываем главное меню
            String mainCommand = sc.nextLine().toLowerCase();  // Считываем команду пользователя

            switch (mainCommand) {
                case "exit" -> {  // Завершение программы
                    sc.close();
                    return;
                }
                case "print" -> {  // Печать содержимого списка
                    if (list.isEmpty()) {
                        System.out.println("Список пуст.");
                    } else {
                        list.forEach(System.out::println);
                    }
                }
                case "add" -> showSubMenu();  // Переход в подменю выбора объекта для добавления
                default -> System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Главное меню:");
        System.out.println("1. Введите 'add', чтобы добавить объект в список");
        System.out.println("2. Введите 'print', чтобы вывести список");
        System.out.println("3. Введите 'exit', чтобы выйти из программы");
        System.out.print("Ваш выбор: ");
    }

    private static void showSubMenu() {
        while (true) {
            System.out.println("Выберите тип объекта для добавления:");
            System.out.println("1. Введите 'person' для добавления Person");
            System.out.println("2. Введите 'animal' для добавления Animal");
            System.out.println("3. Введите 'barrel' для добавления Barrel");
            System.out.println("4. Введите 'back', чтобы вернуться в главное меню");
            System.out.print("Ваш выбор: ");
            String subCommand = sc.nextLine().toLowerCase();

            if (subCommand.equals("back")) {
                return;  // Возврат в главное меню
            }

            ContextCreationStrategy<?> context = ContextCreationStrategy.getContext(subCommand);
            if (context != null) {
                System.out.println("Сколько объектов вы хотите добавить в список?");
                int count = number(sc.nextLine());
                list.addAll(context.executeStrategy(count));  // Добавляем созданные объекты в список
                System.out.println("Добавлено " + count + " объектов.");
            } else {
                System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    private static int number(String st) {
        if (Validator.isValidData(st, DataType.NUMBER)) {
            return Integer.parseInt(st);  // Преобразуем строку в число
        } else {
            System.out.println("Введите целое положительное число!");
            return number(sc.nextLine());  // Повторный запрос ввода, если ввод некорректен
        }
    }
}
