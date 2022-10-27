package com.example.restfulwebservice.halloween;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class HalloweenDaoService {
    private static List<Halloween> monsters = new ArrayList<>();

    private  static int monstersCount = 3;

    static {
        monsters.add(new Halloween(1, "vampire", new Date()));
        monsters.add(new Halloween(2, "GreenBee", new Date()));
        monsters.add(new Halloween(3, "ghost", new Date()));
    }

    public List<Halloween> findAll() {
        return monsters;
    }

    public Halloween save(Halloween halloween){
        if (halloween.getAge() == null) {
            halloween.setAge(++monstersCount);
        }

        monsters.add(halloween);
        return halloween;
    }

    public Halloween findOne(int age) {
        for (Halloween halloween : monsters) {
            if (halloween.getAge() == age) {
                return halloween;
            }
        }
            return null;
    }

    public Halloween deleteByAge(int age) {
        Iterator<Halloween> iterator = monsters.iterator();

        while (iterator.hasNext()) {
            Halloween halloween = iterator.next();

            if(halloween.getAge() == age) {
                iterator.remove();
                return halloween;
            }
        }

        return null;
    }
}
