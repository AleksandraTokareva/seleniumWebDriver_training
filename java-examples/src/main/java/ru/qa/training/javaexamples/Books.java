package ru.qa.training.javaexamples;

import java.util.ArrayList;
import java.util.List;

public class Books {
    static List<String> books = new ArrayList<>();
    static String currentBook;

    public static void main(String[] args) {
        books.add("java 2");
        books.add("java 1");
        currentBook = "java 1";
        System.out.println(nextBook());
    }

    public static String nextBook() {
        for (int i = 0; i < books.size(); i++) {
            if(!(books.get(i).equals(currentBook))) {

                return books.get(i);
            }
        }
        return "";
    }
}