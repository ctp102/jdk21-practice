package com.demo.jdk21practice.domain;

public record Student(
        String name, int age
) {

    // 아래와 같이 추가적인 생성자를 정의할 수도 있다.
    public Student(String name) {
        this(name, 0);
    }

    public Student(int age) {
        this("anonymous", age);
    }

}
