package com.demo.jdk21practice.controller;

import com.demo.jdk21practice.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public String student(@RequestParam String name) {

        Student obj = new Student(name);

        // jdk 16 Record Pattern
        if (obj instanceof Student student) {
            System.out.println(student.name());
        }

        // jdk 21 Record Pattern
        // 오직 record에 한해서만 가능하다
        if (obj instanceof Student(String _name)) {
            System.out.println(_name);
        }

        return null;
    }

}

