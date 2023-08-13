package hello.core;

import lombok.Getter;
import lombok.Setter;

// getter setter 메소드를 자동으로 만들어주는 어노테이션
@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfasdf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
