import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.enums.Classes;
import customClasses.factory.AnimalFactory;
import customClasses.factory.BarrelFactory;
import customClasses.factory.PersonFactory;
import strategy.Actions;
import strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListClasses<T> {
    List<T> list;
    Strategy<Function<Integer, List<T>>> load;

    public static ListClasses generateList(Classes type){
        return switch (type) {
            case ANIMAL -> new ListClasses<Animal>();
            case PERSON -> new ListClasses<Person>();
            case BARREL -> new ListClasses<Barrel>();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    private ListClasses(){
        list = new ArrayList<>();
        load = new Strategy<>();
    }

    private void execute(String name, int count) {
        list = this.load.get(name).apply(count);
    }

    public void executeAll(int count) {
        if(!load.isEmpty())
            this.execute(Actions.LOAD.name, count);
    }


}
