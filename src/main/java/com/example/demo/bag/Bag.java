package com.example.demo.bag;

import java.util.HashMap;
import java.util.Map;

public class Bag<T> {

    protected Map<Object, Object> map = new HashMap<>();

    public Bag(){
        this.clearMap();
    }

    public static Bag create(){
        return new Bag();
    }

    public T add(String key, String value){
        this.map.put(key,value);
        return (T)this;
    }

    public T add(String key, Object value){
        this.map.put(key,value);
        return (T) this;
    }

    public Map<Object,Object> compact(){
        return this.map;
    }

    private void clearMap(){
        this.map.clear();
    }
}
