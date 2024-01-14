package com.demo.jdk21practice.controller;

import com.demo.jdk21practice.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/student")
    public String student(@RequestParam String name, @RequestParam int age) {

        Student obj = new Student(name, age);

        // jdk 16 Record Pattern
        if (obj instanceof Student student) {
            System.out.println(student.name());
            System.out.println(student.age());
        }

        // jdk 21 Record Pattern
        // 오직 record에 한해서만 가능하다
        if (obj instanceof Student(String _name, String _age)) {
            System.out.println(_name);
            System.out.println(_age);
        }

        return null;
    }

    @GetMapping("/student2")
    public String student2() {
        List<Student> students = List.of(
                new Student("김김김", 100),
                new Student("이이이", 200),
                new Student("최최최", 300),
                new Student("박박박", 400),
                new Student("조조조", 500)
        );

        // jdk 21에서는 아직 미지원
        for (Student(String name, int age) : students) {
            System.out.println(name);
            System.out.println(age);
        }

        return "ok";
    }

}

