package customClasses;

import customClasses.enums.Classes;

import strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

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
