import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.enums.Classes;
import customClasses.factory.RandomCreatable;
import customClasses.factory.RandomFactory;
import customClasses.factory.loader.LoaderFactory;
import strategy.Actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // для понимания инициализируем наши фабрики и потом с помощью них создаем объект на выбор
        // Создание случайного животного
        RandomCreatable<Animal> animalFactory = (RandomCreatable<Animal>) RandomFactory.getFactory(Classes.ANIMAL);
        Animal randomAnimal = animalFactory.createRandom();
        Animal randomAnimal2 = animalFactory.createRandom();
        System.out.println(randomAnimal);
        System.out.println(randomAnimal2);

        // Создание случайного человека
        RandomCreatable<Person> personFactory = (RandomCreatable<Person>) RandomFactory.getFactory(Classes.PERSON);
        Person randomPerson = personFactory.createRandom();
        System.out.println(randomPerson);

        // Создание случайной бочки
        RandomCreatable<Barrel> barrelFactory = (RandomCreatable<Barrel>) RandomFactory.getFactory(Classes.BARREL);
        Barrel randomBarrel = barrelFactory.createRandom();
        System.out.println(randomBarrel);

        System.out.println();
        System.out.println("===============STRATEGY================");
        System.out.println("================Random===============");
        /*
        реализация факрики через стратегию
         */
        Classes type = Classes.ANIMAL; // иммитация выбора пользователя
        ListClasses<?> list = ListClasses.generateList(type);
        Actions action = Actions.LOAD_RANDOM; // иммитация выбора источника данных
        action.addStrategy(list.load::add, (count)->
                LoaderFactory.getFactory(action).load(type, count)
        );
        try {
            list.executeAll(10);
        }
        catch (IllegalArgumentException e){
            System.err.println(e);
        }
        list.list.forEach(System.out::println);

        System.out.println("================File===============");

        Classes type1 = Classes.PERSON; // иммитация выбора пользователя
        ListClasses<?> list1 = ListClasses.generateList(type1);
        Actions action1 = Actions.LOAD_FILE; // иммитация выбора источника данных
        action1.addStrategy(list1.load::add, (count)->
                LoaderFactory.getFactory(action1).load(type1, count)
        );
        try {
            list1.executeAll(15);
        }
        catch (RuntimeException e){
            System.err.println(e);
        }
        list1.list.forEach(System.out::println);


        /*
        консоль
         */
        System.out.println();
        System.out.println("===============STRATEGY================");
        System.out.println("================Console===============");
        Classes type2 = Classes.ANIMAL; // иммитация выбора пользователя
        ListClasses<?> list2 = ListClasses.generateList(type2);
        Actions action2 = Actions.LOAD_CONSOLE; // иммитация выбора источника данных
        action2.addStrategy(list2.load::add, (count)->
                LoaderFactory.getFactory(action2).load(type2, count)
        );
        try {
            list2.executeAll(10);
        }
        catch (IllegalArgumentException e){
            System.err.println(e);
        }
        list2.list.forEach(System.out::println);

    }
}