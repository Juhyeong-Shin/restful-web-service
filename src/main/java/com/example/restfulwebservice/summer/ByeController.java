package com.example.restfulwebservice.summer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {
    //get
    //hello (endpoint)
    //@RequestMapping(method=RequestMethod.GET, path="/bye")
    @GetMapping(path = "/Bye")
    public String Bye() {
        return "Bye";
    }

    @GetMapping(path = "/Bye-bean")
    public Byebean byebean() {
        return new Byebean("bye");
    }

    @GetMapping(path = "/Bye-bean/path-variable/{name}")
    public Byebean byebean(@PathVariable String name) {
        return new Byebean(String.format("bye, %s", name));
    }

}
