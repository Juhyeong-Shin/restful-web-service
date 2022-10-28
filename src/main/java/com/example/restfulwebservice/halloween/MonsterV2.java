package com.example.restfulwebservice.halloween;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Monster InfoV2")
public class MonsterV2 extends Monster{
    private String grade;
}
//@JsonIgnoreProperties(value = {"password"})