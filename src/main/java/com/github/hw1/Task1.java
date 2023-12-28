package com.github.hw1;

public class Task1 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing argument NAME");
            return;
        }
        System.out.printf("Greetings, %s!", args[0]);
    }
}
