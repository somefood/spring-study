package me.seokju.mybatisstudy.repository;

import me.seokju.mybatisstudy.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
