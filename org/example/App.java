package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Person {
    String name;
    int age;
    String[] phones;
    Address address;

//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public String[] getPhones() {
//        return phones;
//    }
//
//    public Address getAddress() {
//        return address;
//  }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + Arrays.toString(phones) +
                ", address=" + address +
                '}';
    }
}
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Address {
    String city;
    String street;
    //    public String getCity() {
//        return city;
//    }
//
//    public String getStreet() {
//        return street;
//    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}

public class App {
    static String JSON_TEXT = """
                            {
                                "name": "Vsevolod",
                                "age": 35,
                                "phones": ["0501111111", "0957777777"],
                                "address" : {
                                    "city": "Kyiv",
                                    "street": "xxxxx"
                                }
                            }
            """;

    public static void main( String[] args ) throws IOException {
        // Jackson - HW
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person = gson.fromJson(JSON_TEXT, Person.class);
        System.out.println(person);

        String json = gson.toJson(person);
        System.out.println(json);

        String jacksonString = new ObjectMapper()
               // .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .writeValueAsString(person);
        System.out.println("The string from Jackson is: " + jacksonString);
        JsonParser jsonParser = new ObjectMapper().createParser(jacksonString);
        Person personOne = jsonParser.readValueAs(Person.class);
        System.out.println("The personOne is: " + personOne);

    }
}
