package me.seokju.mybatisstudy.vo;

public class Member {

    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
