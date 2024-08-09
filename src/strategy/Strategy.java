package strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Strategy<T> {
    Map<String, T> map = new HashMap<>();

    public void add(String name, T func) {
        this.map.put(name, func);
    }
    public T get(String name) {
        assertKeyName(name);
        return this.map.get(name);
    }

    private void assertKeyName(String key){
        Set<String> keys = map.keySet();
        if(!keys.contains(key))
            throw new RuntimeException("No action " + key);
    }
}
