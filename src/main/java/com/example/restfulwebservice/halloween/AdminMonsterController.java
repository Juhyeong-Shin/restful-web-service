package com.example.restfulwebservice.halloween;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminMonsterController {
    private MonsterDaoService service;

    public AdminMonsterController(MonsterDaoService service){
        this.service = service;
    }

    @GetMapping("/halloween")
    public MappingJacksonValue retrieveAllMonsters() {
        List<Monster> monsters = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("age","user","joinDate","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("Monster Info",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(monsters);
        mapping.setFilters(filters);

        return mapping;
    }       //전체사용자조회

    @GetMapping("/v1/halloween/{age}")
    public MappingJacksonValue retrieveMonstersV1(@PathVariable int age){
        Monster monster = service.findOne(age);

        if(monster == null) {
            throw new MonsterNotFoundException(String.format("AGE[%s] not found" , age));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("age","user","joinDate","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("Monster Info",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(monster);
        mapping.setFilters(filters);

        return mapping;
    }   //부분사용자조회

    @GetMapping("/v2/halloween/{age}")
    public MappingJacksonValue retrieveMonstersV2(@PathVariable int age){
        Monster monster = service.findOne(age);

        if(monster == null) {
            throw new MonsterNotFoundException(String.format("AGE[%s] not found" , age));
        }

        //Monster -> monster2
        MonsterV2 monsterV2 = new MonsterV2();
        BeanUtils.copyProperties(monster, monsterV2);
        monsterV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("age","user","joinDate","grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("Monster InfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(monster);
        mapping.setFilters(filters);

        return mapping;
    }
}
