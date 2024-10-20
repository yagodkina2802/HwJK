package org.example.homeWork3.task1;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;

    public Person(String namePerson) {
    }

    public String getName() {
        return name;
    }



    public int getAge() {
        return age;
    }



    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
