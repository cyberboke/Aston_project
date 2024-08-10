package customClasses.factory.loader;

import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.enums.Classes;
import customClasses.enums.EyeColor;
import customClasses.enums.Material;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleLoader<T> implements Loader<T> {
    Scanner input = new Scanner(System.in);

    @Override
    public List<T> load(Classes type, int count) {
        List<T> list = new ArrayList<>();

        switch (type) {
            case ANIMAL -> {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter animal " + (i + 1) + "/" + count);

                    Animal.Builder animalBuilder = Animal.builder();

                    System.out.println("Enter animal kind: ");
                    animalBuilder.kind(inputLine());
                    System.out.println("Enter animal hair (no|yes): ");
                    animalBuilder.hair(inputBoolean("no", "yes"));
                    System.out.println("Enter animal eye: ");
                    printEnum(EyeColor.values());

                    animalBuilder.eyeColor(EyeColor.values()[inputInteger(EyeColor.values().length - 1)]);
                    input.nextLine();

                    list.add((T) animalBuilder.build());

                }
            }
            case BARREL -> {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter barrel " + (i + 1) + "/" + count);
                    Barrel.Builder barrelBuilder = Barrel.builder();
                    System.out.println("Enter barrel material: ");
                    printEnum(Material.values());


                    barrelBuilder.material(Material.values()[inputInteger(Material.values().length - 1)]);

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
                    personBuilder.gender(inputBoolean("m", "f"));
                    System.out.println("Enter person age (max - 120): ");
                    personBuilder.age(inputInteger(120));

                    list.add((T) personBuilder.build());
                }
            }
        }


        return list;

    }

    private boolean inputBoolean(String falseParam, String trueParam) {
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


    private int inputInteger(int max) {
        while (true) {
            try {
                int value = input.nextInt();
                if (value > max || value < 0) {
                    System.out.println("Number should be between: 0 and " + max);
                    continue;
                }

                return value;

            } catch (InputMismatchException e) {
                System.out.println("Enter a number");
                input.nextLine();
            }
        }
    }

    private String inputLine() {
        while (true) {
            String str = input.nextLine();
            if ((!str.equals(""))
                    && (str != null)
                    && (str.matches("^[a-zA-Z]*$"))) {
                return str;
            }
            System.out.println("Enter a string of only letters ");

        }
    }

    private <T extends Enum<T>> void printEnum(T[] enumValues) {
        for (int i = 0; i < enumValues.length; i++) {
            System.out.println(i + "." + enumValues[i].name());
        }
    }
}


