package org.example.homeWork3.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.*;
import java.util.*;

public class PersonList {
    public static final String FILE_JSON = "person.json";
    public static final String FILE_BIN = "person.bin";
    public static final String FILE_XMl = "person.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();


    public static void addPerson(Scanner scanner, Map<String,Integer> persons ) throws IOException {
        System.out.println("Введите имя человека и его возраст через пробел");
        String namePerson = scanner.nextLine();
        System.out.println("Введите его возраст");
        int agePerson = Integer.parseInt(scanner.nextLine());
        persons.put(namePerson, agePerson);
        savePersonsToFile(FILE_JSON, persons);
        savePersonsToFile(FILE_BIN, persons);
        savePersonsToFile(FILE_XMl, persons);
        System.out.println("Новый человек добавлен.");
    }

    public static void savePersonsToFile(String fileName, Map<String,Integer> persons) throws IOException {
        try {
            if(fileName.endsWith(".json")){
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), persons);
            } else if (fileName.endsWith(".bin")) {
                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
                    objectOutputStream.writeObject(persons);
                }
            } else if (fileName.endsWith(".xml")) {
                String string = xmlMapper.writeValueAsString(persons);
                xmlMapper.writeValue(new File(fileName), persons);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Map<String,Integer>  loadPersonsFromFile(String fileName) {
        Map<String,Integer> persons = new HashMap<String, Integer>();

        File file = new File(fileName);
        if(file.exists()){
            try{
                if(fileName.endsWith(".json")){
                    persons = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, PersonV2.class));
                }else if(fileName.endsWith(".bin")){
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                        persons = (HashMap<String, Integer>)ois.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (fileName.endsWith(".xml")) {
                    persons = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, PersonV2.class));
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return persons;
    }

    public static void  displayPerson(Map<String,Integer> persons){
        System.out.println("Список людей: ");
        for(Map.Entry<String, Integer> entry : persons.entrySet()){
            System.out.println("Имя: " + entry.getKey() + ", Возраст: " + entry.getValue());
        }
    }
    public static void deletePerson(Scanner scanner, Map<String,Integer> persons) throws IOException {
        System.out.println("Введите имя человека, которого хотите удалить");
        String namePerson = scanner.nextLine();
        for (int i = 0; i < persons.size(); i++) {
            if(persons.containsKey(namePerson)){
                persons.remove(namePerson);
                savePersonsToFile(FILE_JSON, persons);
                savePersonsToFile(FILE_BIN, persons);
                savePersonsToFile(FILE_XMl, persons);
            }else System.out.println("Имя введено неверно");
        }


    }

}
