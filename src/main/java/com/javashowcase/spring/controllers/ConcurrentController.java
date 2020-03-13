package com.javashowcase.spring.controllers;

import static java.lang.System.out;

import com.javashowcase.spring.objects.SquareCalculator;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/concurrent")
public class ConcurrentController {

    @SneakyThrows
    @GetMapping(path = "/future")
    public int futureDemo(@RequestParam("input") Integer value) {
        Future<Integer> future = new SquareCalculator().calculate(value);

        while(!future.isDone()) {
            out.println("Calculating...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        out.println("Done calculating!");
        return future.get();
    }
}
