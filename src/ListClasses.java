import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import customClasses.enums.Classes;

import strategy.Actions;
import strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ListClasses<T> {
    List<T> list;

    Strategy<Runnable> strategy;

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
        strategy = new Strategy<>();
    }
    private void execute(String name) {
           this.strategy.get(name).run();
    }
    public void executeAll() {
        this.strategy.map.keySet().forEach(this::execute);
    }


}
