import customClasses.Animal;
import customClasses.Barrel;
import customClasses.Person;
import enums.TypeClass;

import strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class ListClasses<T> {
    List<T> list;

    Strategy<Runnable> strategy;

    public static ListClasses generateList(TypeClass type){
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
    public void execute(String name) {
           this.strategy.get(name).run();
    }
    public void executeAll() {
        this.strategy.map.keySet().forEach(this::execute);
    }

    public Strategy<Runnable> getStrategy() {
        return strategy;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
