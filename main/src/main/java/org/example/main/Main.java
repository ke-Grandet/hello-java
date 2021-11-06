package org.example.main;

public class Main {

    public static void main(String[] args) {
        Temp temp = new Temp();
        temp.run(null);
    }

    static class Temp {
        public void run(Integer i ) {
            System.out.println(String.valueOf(i));
        }
    }

}
