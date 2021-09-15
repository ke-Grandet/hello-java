package org.example.main.other;

import java.io.*;

public class Serializable {

    private static final String file = "file.txt";

    public static class User implements java.io.Serializable {
        public static final long serialVersionUID = 1L;
        public String name;
        public int age;
    }

    public void writeUser() {
        try {
            User user = new User();
            user.name = "john";
            user.age = 22;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(new File(file))
            );
            objectOutputStream.writeObject(user);
            System.out.println("序列化成功");
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("write(): " + e.getMessage());
        }
    }

    public void readUser() {
        User user = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(new File(file))
            );
            user = (User) objectInputStream.readObject();
            System.out.println("反序列化成功");
            System.out.println("name: " + user.name + "\t age: " + user.age);
        } catch (Exception e) {
            System.out.println("read(): " + e.getMessage());
        }

    }
}
