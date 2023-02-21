package me.seokju.mybatisstudy.vo;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long member_id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public Long getMember_id() {
        return member_id;
    }

    public String getName() {
        return name;
    }
}
