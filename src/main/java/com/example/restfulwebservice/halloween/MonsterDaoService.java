package com.example.restfulwebservice.halloween;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class MonsterDaoService {
    private static List<Monster> users = new ArrayList<>();

    private  static int monstersCount = 3;

    static {
        users.add(new Monster(1, "vampire", new Date(), "www", "1234"));
        users.add(new Monster(2, "GreenBee", new Date(), "sss","12345"));
        users.add(new Monster(3, "ghost", new Date(), "ppp","123456"));
    }

    public List<Monster> findAll() {
        return users;
    }

    public Monster save(Monster monster){
        if (monster.getAge() == null) {
            monster.setAge(++monstersCount);
        }

        users.add(monster);
        return monster;
    }

    public Monster findOne(int age) {
        for (Monster monster : users) {
            if (monster.getAge() == age) {
                return monster;
            }
        }
            return null;
    }

    public Monster deleteByAge(int age) {
        Iterator<Monster> iterator = users.iterator();

        while (iterator.hasNext()) {
            Monster monster = iterator.next();

            if(monster.getAge() == age) {
                iterator.remove();
                return monster;
            }
        }

        return null;
    }
}
