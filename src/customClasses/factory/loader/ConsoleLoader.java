package customClasses.factory.loader;

import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import enums.TypeClass;
import enums.EyeColor;
import enums.Material;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleLoader<T> implements Loader<T> {
    private static Scanner input = new Scanner(System.in);

    @Override
    public List<T> load(TypeClass type, int count) {
        List<T> list = new ArrayList<>();

        //выбор типа объекта для заполнения
        switch (type) {
            case ANIMAL -> {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter animal " + (i + 1) + "/" + count);

                    Animal.Builder animalBuilder = Animal.builder();// создание объекта

                    //заполнение полей объекта
                    System.out.println("Enter animal kind: ");
                    animalBuilder.kind(inputLine());
                    System.out.println("Enter animal hair (no|yes): ");
                    animalBuilder.hair(inputBoolean("no", "yes"));
                    System.out.println("Enter animal eye: ");
                    //считывание значения enum
                    animalBuilder.eyeColor(ConsoleLoader.inputEnum(EyeColor.values()));

                    input.nextLine();

                    //добавляем созданный объект в лист
                    list.add((T) animalBuilder.build());

                }
            }
            case BARREL -> {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter barrel " + (i + 1) + "/" + count);
                    Barrel.Builder barrelBuilder = Barrel.builder();
                    System.out.println("Enter barrel material: ");
                    barrelBuilder.material(ConsoleLoader.inputEnum(Material.values()));

                    System.out.println("Enter barrel volume (max = 1000): ");
                    barrelBuilder.volume(inputInteger(1000));
                    System.out.println("Enter barrel storageMaterial: ");
                    barrelBuilder.storageMaterial(inputLine());

                    list.add((T) barrelBuilder.build());
                }

            }
            case PERSON -> {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter person " + (i + 1) + "/" + count);
                    Person.Builder personBuilder = Person.builder();
                    System.out.println("Enter person surname: ");
                    personBuilder.surname(inputLine());
                    System.out.println("Enter person gender (m - male | f - female): ");
                    personBuilder.gender(inputBoolean("f", "m"));
                    System.out.println("Enter person age (max - 120): ");
                    personBuilder.age(inputInteger(120));

                    list.add((T) personBuilder.build());
                }
            }
        }

        return list;

    }

    /**
     * ввод значения boolean
     * @param falseParam - символ для false значения
     * @param trueParam - символ для true значения
     * @return - введенное значение
     */
    public static boolean inputBoolean(String falseParam, String trueParam) {
        while (true) {

            String str = input.nextLine();
            if(str.equals(falseParam)) {
                return false;
            } else if(str.equals(trueParam)) {
                return true;
            } else {
                System.out.println("Enter " + falseParam + " or " + trueParam);
            }
        }
    }

    /**
     * ввод значения int
     * @param max - максимально допустимое значение
     * @return - введенное значение
     */
    public static int inputInteger(int max) {
        return inputInteger(0, max);
    }

    /**
     * ввод значения int
     * @param min - минимально допустимое значение
     * @param max - максимально допустимое значение
     * @return - введенное значение
     */
    public static int inputInteger(int min, int max) {
        while (true) {
            try {
                int value = input.nextInt();
                if (value > max || value < min) {
                    System.out.println("Number should be between: " + min + " and " + max);
                    continue;
                }

                return value;

            } catch (InputMismatchException e) {
                System.out.println("Enter a number");
                input.nextLine();
            }
        }
    }

    /**
     * ввод значения String
     * @return - введенное значение
     */
    public static String inputLine() {
        while (true) {
            String str = input.nextLine();
            // regexp: латинские и русские буквы, пробел, тире
            if ((!str.equals(""))
                    && (str != null)
                    && (str.matches("^[a-zA-Zа-яА-Я\\s\\-]*$"))) {
                return str;
            }
            System.out.println("Enter a string of only letters ");

        }
    }

    /**
     * вывод enum
      * @param enumValues - массив значения enum
     * @param <T> - тип enum
     */
    public static <T extends Enum<T>> void printEnum(T[] enumValues) {
        for (int i = 0; i < enumValues.length; i++) {
            System.out.println(i + "." + enumValues[i].name());
        }
    }

    /**
     * ввод enum
     * @param enumValues - массив значения enum
     * @return - выбранный enum
     * @param <T> - тип enum
     */
    public static <T extends Enum<T>> T inputEnum(T[] enumValues) {
        printEnum(enumValues);
        return enumValues[inputInteger(enumValues.length - 1)];
    }
}


