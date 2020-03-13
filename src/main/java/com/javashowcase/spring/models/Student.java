package com.javashowcase.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String group;
    private int age;
    private double grade;
}
