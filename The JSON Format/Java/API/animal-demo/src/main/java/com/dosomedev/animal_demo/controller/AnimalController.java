package com.dosomedev.animal_demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dosomedev.animal_demo.model.Animal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AnimalController {
    @GetMapping("/animals")
    public ArrayList<Animal> animals() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Dog", "Max", "Golden Retriever", 2));
        animals.add(new Animal("Cat", "Luna", "Siamese", 1));
        animals.add(new Animal("Duck", "Daisy", "Welsh Harlequin", 3));
        animals.add(new Animal("Rabbit", "Poppy", "Lionhead", 4));

        return animals;
    }
}
