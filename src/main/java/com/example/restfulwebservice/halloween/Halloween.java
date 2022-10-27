package com.example.restfulwebservice.halloween;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Halloween {
    private Integer age;
    private String monster;
    private Date joinDate;
}
