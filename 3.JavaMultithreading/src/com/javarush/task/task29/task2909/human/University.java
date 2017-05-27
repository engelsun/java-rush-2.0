package com.javarush.task.task29.task2909.human;

import java.util.*;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student :
                students) {
            if (student.getAverageGrade() == averageGrade) return student;
        }
        return null;
    }

//    public static void main(String[] args) {
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(new Student("ann", 23, 94.7));
//        studentList.add(new Student("nik", 23, 94.8));
//        studentList.add(new Student("gas", 23, 99.0));
//        studentList.add(new Student("tom", 23, 100));
//        studentList.add(new Student("tom2", 23, 101));
//        studentList.stream().forEach(Student::printData);
//        System.out.println("==========================================");
//        University university = new University("oxford", 223);
//        university.setStudents(studentList);
//        Student studentAverage = university.getStudentWithAverageGrade(99);
//        studentAverage.printData();
//        Student studentMax = university.getStudentWithMaxAverageGrade();
//        studentMax.printData();
//        System.out.println("==========================================");
//        Student studentMin = university.getStudentWithMinAverageGrade();
//        studentMin.printData();
//        university.expel(studentMin);
//        System.out.println("==========================================");
//        studentList.stream().forEach(Student::printData);
//        System.out.println("==========================================");
//        Date begin = new Date(2010, 9, 1);
//        Date end = new Date(2016, 6, 1);
//        StudentsDataBase.students.addAll(studentList);
//        StudentsDataBase.removeStudent(4);
//        StudentsDataBase.students.stream().forEach(Student::printData);
//
//    }

    public Student getStudentWithMaxAverageGrade() {
        Student student = students.stream()
                .max(Comparator.comparingDouble(Student::getAverageGrade))
                .get();
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = students.stream()
                .min(Comparator.comparingDouble(Student::getAverageGrade))
                .get();
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}