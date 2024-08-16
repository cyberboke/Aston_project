package menu;

import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.comparators.registry.ComparatorRegistry;
import customClasses.factory.loader.ConsoleLoader;
import enums.*;

import java.util.*;


public class Menu {

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
                System.out.println("0 - default, 1 - Kind, 2 - Eye, 3 - Hair: ");
                break;
            case PERSON:
                System.out.println("0 - default, 1 - Age, 2 - Surname, 3 - Gender: ");
                break;
            case BARREL:
                System.out.println("0 - default, 1 - StoredMaterial, 2 - Material, 3 - Volume: ");
                break;
        }
        int input = ConsoleLoader.inputInteger(0, comparators.size());
        if(input == 0) return null;
        return comparators.get(input - 1);
    }

    /**
     * Создание объекта для поиска
     * @param typeClass - тип объекта
     * @return - объект Object
     */
    public static Object createObject(TypeClass typeClass){
        Object obj = null;
        switch (typeClass) {
            case ANIMAL -> {
                Animal.Builder animalBuilder = Animal.builder();// создание объекта
                //заполнение полей объекта
                System.out.println("Enter animal kind: ");
                animalBuilder.kind(ConsoleLoader.inputLine());
                System.out.println("Enter animal hair (no|yes): ");
                animalBuilder.hair(ConsoleLoader.inputBoolean("no", "yes"));
                System.out.println("Enter animal eye: ");
                //считывание значения enum
                animalBuilder.eyeColor(ConsoleLoader.inputEnum(EyeColor.values()));
                obj = animalBuilder.build();
            }
            case PERSON -> {
                Person.Builder personBuilder = Person.builder();
                System.out.println("Enter person surname: ");
                personBuilder.surname(ConsoleLoader.inputLine());
                System.out.println("Enter person gender (m - male | f - female): ");
                personBuilder.gender(ConsoleLoader.inputBoolean("m", "f"));
                System.out.println("Enter person age (max - 120): ");
                personBuilder.age(ConsoleLoader.inputInteger(120));
                obj = personBuilder.build();
            }
            case BARREL ->{
                Barrel.Builder barrelBuilder = Barrel.builder();
                System.out.println("Enter barrel material: ");
                barrelBuilder.material(ConsoleLoader.inputEnum(Material.values()));

                System.out.println("Enter barrel volume (max = 1000): ");
                barrelBuilder.volume(ConsoleLoader.inputInteger(1000));
                System.out.println("Enter barrel storageMaterial: ");
                barrelBuilder.storageMaterial(ConsoleLoader.inputLine());
                obj =  barrelBuilder.build();
            }
        };
        return obj;
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
