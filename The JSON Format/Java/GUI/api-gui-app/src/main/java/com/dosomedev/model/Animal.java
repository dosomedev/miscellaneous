package com.dosomedev.model;

public class Animal {
    private String animal;
    private String name;
    private String breed;
    private Integer age;

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Animal() {}

    public Animal(String animal, String name, String breed, Integer age) {
        this.animal = animal;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }
}
