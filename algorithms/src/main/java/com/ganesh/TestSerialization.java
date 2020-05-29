package com.ganesh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable {
    String name;
    int age;
    String company;
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class TestSerialization {

    private static void printData(Employee object1) {
        System.out.println("name = " + object1.name);
        System.out.println("age = " + object1.age);
    }

    public static void main(String[] args) {
        deserialize("shubham.txt");
//        Employee object = new Employee("ab", 20);
//        String filename = "shubham.txt";
//        serialize(object, filename);
    }

    private static void deserialize(String filename) {
        // Deserialization
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream
                    (filename);
            ObjectInputStream in = new ObjectInputStream
                    (file);

            // Method for deserialization of object
            Employee object = (Employee) in.readObject();

            in.close();
            file.close();
            System.out.println("Object has been deserialized\n" + "Data after Deserialization.");
            printData(object);

            // System.out.println("z = " + object1.z);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }

    }

    private static void serialize(Employee object, String filename) {
        // Serialization
        try {

            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized\n" + "Data before Deserialization.");
            printData(object);
            System.out.println(object.hashCode());
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

}
