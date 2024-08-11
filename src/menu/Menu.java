package menu;

import customClasses.ListClasses;
import customClasses.enums.Classes;
import customClasses.enums.TypeLoad;
import customClasses.factory.loader.LoaderFactory;
import strategy.Actions;

import java.util.List;
import java.util.Scanner;

public class Menu {
    boolean isExit = false; // флаг выхода из программы
    MenuEntry mainEntry; // указатель на главный уровень меню
    MenuEntry currentEntry; //указатель на текущий уровень меню

    Classes currentClass = Classes.PERSON;
    int count = 1;
    ListClasses<?> listConsole;

    public Menu() {
        this.mainEntry = new MenuEntry("Main Menu"); // создаем объект главного меню
        this.currentEntry = mainEntry; // текущему уромню меню присваивается главный уровень

        // заполняем первый уровень меню
        MenuEntry selectTypeEntry = new MenuEntry("Select type");
        MenuEntry operationsEntry = new MenuEntry("Operations");

        mainEntry.getEntries().add(selectTypeEntry);
        mainEntry.getEntries().add(operationsEntry);

        // уровень меню Select type
        MenuEntry personEntry = new MenuEntry("Person", () -> { this.currentClass = Classes.PERSON;});
        MenuEntry animalEntry = new MenuEntry("Animal", () -> { this.currentClass = Classes.ANIMAL;});
        MenuEntry barrelEntry = new MenuEntry("Barrel", () -> { this.currentClass = Classes.BARREL;});
        selectTypeEntry.getEntries().add(personEntry);
        selectTypeEntry.getEntries().add(animalEntry);
        selectTypeEntry.getEntries().add(barrelEntry);

        // уровень меню Operations
        MenuEntry loadEntry = new MenuEntry("Load");
        MenuEntry updateEntry = new MenuEntry("Update");
        MenuEntry printEntry = new MenuEntry("Print", () -> {
            if (this.listConsole != null) {
                this.listConsole.getList().forEach(System.out::println);
            }
        });
        operationsEntry.getEntries().add(loadEntry);
        operationsEntry.getEntries().add(updateEntry);
        operationsEntry.getEntries().add(printEntry);

        // уровень меню Load
        MenuEntry fileEntry = new MenuEntry("File");
        MenuEntry randomEntry = new MenuEntry("Random");
        MenuEntry consoleEntry = new MenuEntry("Console", () -> {
            // вызываем ConsoleLoader
            this.listConsole = ListClasses.generateList(this.currentClass);
            TypeLoad typeLoadConsole = TypeLoad.LOAD_CONSOLE;  // иммитация выбора источника данных
            Actions.LOAD.addStrategy(listConsole.getStrategy()::add,
                    () -> {
                        List filledList = LoaderFactory.getFactory(typeLoadConsole).load(this.currentClass, this.count);
                        this.listConsole.setList(filledList);
            });

            try {
                listConsole.executeAll();
            } catch (IllegalArgumentException e){
                System.err.println(e);
            }
        });
        loadEntry.getEntries().add(fileEntry);
        loadEntry.getEntries().add(randomEntry);
        loadEntry.getEntries().add(consoleEntry);

    }
    // Метод выводит текущий список меню
    public void printEntries() {
        System.out.println(currentEntry.getTitle() + " " + "(" + currentClass.name() + ")");
        // цикл перебирает пунты меню и выводит выбор пользователя
        for (int i = 0; i < currentEntry.getEntries().size(); i++) {
            System.out.println(i + " " + currentEntry.getEntries().get(i).getTitle());

        }
        // Добавление пункта Exit в конец каждого вывода
        System.out.println(currentEntry.getEntries().size() + " Exit");

    }

    public void run() {
        // Бесконечный цикл, пока не нажали кнопку выход
        Scanner input = new Scanner(System.in);
        while (!isExit) {
            currentEntry.run();
            printEntries();

            int choice = input.nextInt(); // считываем выбор пользователя
            if (choice < currentEntry.getEntries().size()) {
                this.currentEntry = this.currentEntry.getEntries().get(choice); // изменение текущего меню на выбор пользователя
            } else {
                if (this.currentEntry == this.mainEntry) {
                    this.isExit = true; // выбран Exit из главного меню, программа завершается
                } else {
                    this.currentEntry = this.mainEntry; //изменение текущего меню на главный пункт меню
                }
            }
        }
    }
}
