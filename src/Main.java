import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.factory.RandomCreatable;
import customClasses.factory.RandomFactory;

public class Main {
    public static void main(String[] args) {
        // для понимания инициализируем наши фабрики и потом с помощью них создаем объект на выбор
        // Создание случайного животного
        RandomCreatable<Animal> animalFactory = (RandomCreatable<Animal>) RandomFactory.getFactory("animal");

        Animal randomAnimal = animalFactory.createRandom();
        Animal randomAnimal2 = animalFactory.createRandom();
        System.out.println(randomAnimal);
        System.out.println(randomAnimal2);

        // Создание случайного человека
        RandomCreatable<Person> personFactory = (RandomCreatable<Person>) RandomFactory.getFactory("person");
        Person randomPerson = personFactory.createRandom();
        System.out.println(randomPerson);

        // Создание случайной бочки
        RandomCreatable<Barrel> barrelFactory = (RandomCreatable<Barrel>) RandomFactory.getFactory("barrel");
        Barrel randomBarrel = barrelFactory.createRandom();
        System.out.println(randomBarrel);


    }
}