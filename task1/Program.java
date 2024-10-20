package org.example.homeWork3.task1;

import org.example.homeWork3.task1.Person;

import java.io.*;

/**Задание 1: Создайте класс Person с полями name и age. Реализуйте сериализацию и десериализацию этого класса в файл.

Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person. Реализуйте методы для
 добавления, обновления и удаления объектов Person.
 */
public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Nastya", 21);

        try(FileOutputStream fileOutputStream = new FileOutputStream("person.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
                objectOutputStream.writeObject(person);
                System.out.println("Person сериализован");
            }

        try (FileInputStream fileInputStream = new FileInputStream("person.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                person = (Person)objectInputStream.readObject();

        }

        System.out.println("Объект Person десерилизован");
        System.out.println("Имя: " + person.getName());
        System.out.println("Возраст: "+ person.getAge());



    }

}
