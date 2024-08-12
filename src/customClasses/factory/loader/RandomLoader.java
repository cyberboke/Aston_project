package customClasses.factory.loader;

import enums.TypeClass;
import customClasses.factory.ramdom.RandomCreatable;
import customClasses.factory.ramdom.RandomFactory;

import java.util.ArrayList;
import java.util.List;

public class RandomLoader<T> implements Loader<T> {


    @Override
    public List<T> load(TypeClass type, int count) {
        List<T> list = new ArrayList<>();
        RandomCreatable<T> randomFactory = (RandomCreatable<T>) RandomFactory.getFactory(type);
        for(int i = 0; i < count; i++){
            list.add(randomFactory.createRandom());
        }
        return list;
    }
}
