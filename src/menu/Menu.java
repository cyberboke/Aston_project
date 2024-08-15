package menu;

import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.comparators.registry.ComparatorRegistry;
import customClasses.factory.loader.ConsoleLoader;
import enums.*;

import java.util.*;


public class Menu {
    static Scanner input = new Scanner(System.in);

    /**
     * Ввод типа объекта
     * @return
     */
    public static TypeClass choosingClass() {
        TypeClass typeClass = null;
        System.out.println("Choose a class: 1 - Animal , 2 - Person, 3 - Barrel: ");
        int select = ConsoleLoader.inputInteger(1, TypeClass.values().length);

        switch (select) {
            case 1:
                typeClass = TypeClass.ANIMAL;
                break;
            case 2:
                typeClass = TypeClass.PERSON;
                break;
            case 3:
                typeClass = TypeClass.BARREL;
                break;
        }
        return typeClass;
    }

    /**
     * Ввод размера массива
     * @return
     */
    public static int choosingSize(){
        System.out.println("Choose size of list (min = 1, max = 100): ");
        return ConsoleLoader.inputInteger(1, 100);
    }

    /**
     * Ввод типа загрузки
     * @return
     */
    public static TypeLoad choosingLoad() {
        TypeLoad typeLoad = null;
        System.out.println("Choose type of load: 1 - Load random,  2 - Load file, 3 - Load console: ");
        int select = ConsoleLoader.inputInteger(1, TypeLoad.values().length);

        switch (select) {
            case 1:
                typeLoad = TypeLoad.LOAD_RANDOM;
                break;
            case 2:
                typeLoad = TypeLoad.LOAD_FILE;
                break;
            case 3:
                typeLoad = TypeLoad.LOAD_CONSOLE;
                break;
        }
        return typeLoad;
    }

    /**
     * Ввод типа действия над списком
     * @return
     */
    public static TypeAction choosingAction() {
        TypeAction typeAction = null;
        System.out.println("Choose an action: 1 - Sort , 2 - Search: " );
        int select = ConsoleLoader.inputInteger(1, TypeAction.values().length);

        switch (select) {
            case 1:
                typeAction = TypeAction.SORT;
                break;
            case 2:
                typeAction = TypeAction.SEARCH;
                break;
        }
        return typeAction;
    }

    /**
     * Ввод типа сортировки
     * @return
     */
    public static TypeSort choosingSort() {
        TypeSort typeSort = null;
        System.out.println("Choose a sort: 1 - Timsort, 2 - Evensort, 3 - Oddsort: ");
        int select = ConsoleLoader.inputInteger(1, TypeSort.values().length);

        switch (select) {
            case 1:
                typeSort = TypeSort.TIMSORT;
                break;
            case 2:
                typeSort = TypeSort.EVENSORT;
                break;
            case 3:
                typeSort = TypeSort.ODDSORT;
                break;
        }
        return typeSort;
    }


    /**
     * Выбор поля объекта для сортировки или поиска
     * @param typeClass - тип объекта
     * @return - возвращает компаратор
     */
    public static Comparator choosingComparator(TypeClass typeClass) {
        System.out.println("Choose a field: ");
        List<Comparator<?>> comparators = ComparatorRegistry.getComparators(typeClass);
        switch (typeClass) {
            case ANIMAL:
                System.out.println("1 - Kind, 2 - Eye, 3 - Hair: ");
                break;
            case PERSON:
                System.out.println("1 - Age, 2 - Surname, 3 - Gender: ");
                break;
            case BARREL:
                System.out.println("1 - StoredMaterial, 2 - Material, 3 - Volume: ");
                break;
        }
        int input = ConsoleLoader.inputInteger(1, comparators.size());
        return comparators.get(input - 1);
    }

    /**
     * Заполнение поля для поиска по списку animal
     * @param comparator - компоратор
     * @return - объект animal
     */
    public static Animal fillAnimal(Comparator comparator) {
        Animal animal = null;
        switch (comparator.toString()) {
            case "AnimalKindComparator":
                System.out.println("Input kind of animal: ");
                animal = Animal.builder().kind(ConsoleLoader.inputLine()).build();
                break;
            case "AnimalEyeColorComparator":
                System.out.println("Input eye color of animal: ");
                animal = Animal.builder().eyeColor(ConsoleLoader.inputEnum(EyeColor.values())).build();
                break;
            case "AnimalHairComparator":
                System.out.println("Input hair of animal (no or yes): ");
                animal = Animal.builder().hair(ConsoleLoader.inputBoolean("no", "yes")).build();
                break;
        }
        return animal;
    }

    /**
     * Заполнение поля для поиска по списку person
     * @param comparator - компоратор
     * @return - объект person
     */
    public static Person fillPerson(Comparator comparator) {
        Person person = null;
        switch (comparator.toString()) {
            case "PersonAgeComparator":
                System.out.println("Input age of person (max - 120): ");
                person = Person.builder().age(ConsoleLoader.inputInteger(120)).build();
                break;
            case "PersonGenderComparator":
                System.out.println("Input gender of person (m - male | f - female): ");
                person = Person.builder().gender(ConsoleLoader.inputBoolean("m", "f")).build();
                break;
            case "PersonSurnameComparator":
                System.out.println("Input surname of person: ");
                person = Person.builder().surname(ConsoleLoader.inputLine()).build();
                break;
        }
        return person;
    }

    /**
     * Заполнение поля для поиска по списку barrel
     * @param comparator - компоратор
     * @return - объект barrel
     */
    public static Barrel fillBarrel(Comparator comparator) {
        Barrel barrel = null;
        switch (comparator.toString()) {
            case "BarrelMaterialComparator":
                System.out.println("Input material of barrel: ");
                barrel = Barrel.builder().material(ConsoleLoader.inputEnum(Material.values())).build();
                break;
            case "BarrelStoredMaterialComparator":
                System.out.println("Input stored material of barrel: ");
                barrel = Barrel.builder().storageMaterial(ConsoleLoader.inputLine()).build();
                break;
            case "BarrelVolumeComparator":
                System.out.println("Input volume of barrel (max - 1000): ");
                barrel = Barrel.builder().volume(ConsoleLoader.inputInteger(1000)).build();
                break;
        }
        return barrel;
    }

    /**
     * Выбор изменения в работе со списком
     * @return тип изменения
     */
    public static TypeChoice choosingChoice() {
        TypeChoice typeChoice = null;
        System.out.println("Choose an action 1 - Change class, 2 - Change load, 3 - Run action, 4 - Exit: ");
        int select = ConsoleLoader.inputInteger(1, TypeChoice.values().length);

        switch (select) {
            case 1:
                typeChoice = TypeChoice.TYPE_CLASS;
                break;
            case 2:
                typeChoice = TypeChoice.TYPE_LOAD;
                break;
            case 3:
                typeChoice = TypeChoice.TYPE_ACTION;
                break;
            case 4:
                typeChoice = TypeChoice.EXIT;
                break;
        }
        return typeChoice;
    }

}
