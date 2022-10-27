package com.example.restfulwebservice.halloween;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HalloweenController {
    private  HalloweenDaoService service;

    public HalloweenController(HalloweenDaoService service){
        this.service = service;
    }

    @GetMapping("/halloween")
    public List<Halloween> retrieveAllHalloweens() {
        return service.findAll();
    }       //전체사용자조회

    @GetMapping("/halloween/{age}")
    public Halloween retrieveHalloween(@PathVariable int age){
        return service.findOne(age);
    }   //부분사용자조회

    @PostMapping("/halloween")
    public void createHalloween(@RequestBody Halloween halloween) {
        Halloween savedHalloween = service.save(halloween);
    }

    public void deleteHalloween(@PathVariable int age) {
        Halloween halloween = service.deleteByAge(age);

        if(halloween == null) {
            throw new hallo
        }
    }
}
