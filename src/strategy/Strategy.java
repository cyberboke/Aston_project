package strategy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Strategy<T> {
    public Map<String, T> map = new LinkedHashMap<>();

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
    public void remove(String name){
        this.map.remove(name);
    }
}
