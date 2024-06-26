package com.james.api.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Data
@Builder
public class Box<T>{

    private PageDto pageDto;
    private List<?> list;
    private Map<T, T> box;

    public Box() {
        this.box = new HashMap<>();
    }
    public void put(List<T> keys, Inventory<T> values){
        for(int i=0; i<keys.size();i++){
            box.put(keys.get(i), values.get(i) );
        }
        box.forEach((k,v)-> System.out.println(String.format("%s: %s", k, v)));

    }
    public T put(T k, T t) {
        return box.put(k, t);
    }

    public T get(String k){
        return box.get(k);
    }

    public int size(){
        return box.size();
    }
    public void clear() {
        box.clear();
    }


}

