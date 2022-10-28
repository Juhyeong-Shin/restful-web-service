package com.example.restfulwebservice.halloween;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class MonsterController {
    private MonsterDaoService service;

    public MonsterController(MonsterDaoService service){
        this.service = service;
    }

    @GetMapping("/halloween")
    public List<Monster> retrieveAllMonsters() {
        return service.findAll();
    }       //전체사용자조회

    @GetMapping("/halloween/{age}")
    public Monster retrieveMonsters (@PathVariable int age){
        Monster monster = service.findOne(age);

        if(monster == null) {
            throw new MonsterNotFoundException(String.format("AGE[%s] not found" , age));
        }

        return monster;
    }   //부분사용자조회

    @PostMapping("/halloween")
    public ResponseEntity<Monster> createMonster (@Valid @RequestBody Monster monster) {
        Monster savedMonster = service.save(monster);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{age}")
                .buildAndExpand(savedMonster.getAge())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping({"/halloween/{age}"})
    public void deleteMonsters(@PathVariable int age) {
        Monster monster = service.deleteByAge(age);

        if(monster == null) {
            throw new MonsterNotFoundException(String.format("AGE[%s] not found", age));
        }
    }
}
