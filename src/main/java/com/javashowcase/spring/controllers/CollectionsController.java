package com.javashowcase.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.out;
import static java.util.stream.Collectors.groupingBy;

import com.javashowcase.spring.models.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @GetMapping(path = "/map")
    public List<Object> listDemo() {
        List<Student> students = Arrays.asList(
                student("Jhon Deany", "B", 23, 4.2),
                student("Catalina Souza", "A", 23, 4.8),
                student("Alex Suarez", "A", 22, 4.0),
                student("Brienne Sanders", "B", 29, 2.0),
                student("Brian Larson", "B", 29, 2.8),
                student("Alice Miller", "A", 20, 3.7)
        );

        out.println("Dev tools Test!");

        Double averageGrade = computeAverage(students);

        Map<String, Double> groupAverage = students
                .stream()
                .collect(groupingBy(Student::getGroup))
                .entrySet()
                .stream()
                .map(group -> Map.entry(group.getKey(), computeAverage(group.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return Arrays.asList(averageGrade, groupAverage);
    }

    private Student student(String name, String group, int age, double grade) {
        return new Student(name, group, age, grade);
    }

    private Double computeAverage(List<Student> students) {
        double average = students.stream()
                .map(Student::getGrade)
                .reduce(Double::sum)
                .orElse(0.0) / students.size();
        return new BigDecimal(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
