package org.example.homeWork3.task2;

/**Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person. Реализуйте методы для
        добавления, обновления и удаления объектов Person.
*/


import org.example.task2.ToDoListApp;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.example.homeWork3.task2.PersonList.*;
import static org.example.homeWork3.task2.PersonList.FILE_BIN;
import static org.example.homeWork3.task2.PersonList.FILE_JSON;
import static org.example.homeWork3.task2.PersonList.FILE_XMl;



public class Main {
    public static void main(String[] args) throws IOException {
        Map<String,Integer> persons = new HashMap<String, Integer>();
        File file = new File(FILE_JSON);
        persons = preparePerson();
        if (file.exists() && !file.isDirectory()) persons = loadPersonsFromFile(FILE_JSON);
        else persons = preparePerson();
        PersonList.savePersonsToFile(FILE_JSON, persons);
        PersonList.savePersonsToFile(FILE_BIN, persons);
        PersonList.savePersonsToFile(FILE_XMl, persons);
        displayPerson(persons);


        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить еловека");
            System.out.println("2 - удалить человека");
            System.out.println("3 - Выйти");
            String choice = scanner.nextLine();

            switch(choice){
                case "1":
                    PersonList.addPerson(scanner, persons);
                    break;
                case"2":
                    PersonList.deletePerson(scanner, persons);
                    break;
                case "3":
                    PersonList.savePersonsToFile(FILE_JSON, persons);
                    PersonList.savePersonsToFile(FILE_BIN, persons);
                    PersonList.savePersonsToFile(FILE_XMl, persons);
                    System.out.println("Список сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неккоректный выбор. Попробуйте снова.");
                    break;
            }
            displayPerson(persons);
        }
    }

    private static Map<String,Integer> preparePerson() {
        Map<String,Integer> persons = new HashMap<String, Integer>();
        persons.put("Olesya", 15);
        persons.put("Olya", 15);
        persons.put("Vitya", 15);

        return persons;
    }





}
