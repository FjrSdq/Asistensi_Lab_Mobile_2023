package com.example.sqlitedatabase;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Student> students = generateDummyStudents();

    private static ArrayList<Student> generateDummyStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(0, "Monkey D. Luffy", "SHP01", "Makan1"));
        students.add(new Student(1, "Roronoa Zoro", "SHP02", "Makan2"));
        students.add(new Student(2, "Nami", "SHP03", "Makan3"));
        students.add(new Student(3, "Usopp", "SHP04", "Makan4"));
        return students;
    }
}