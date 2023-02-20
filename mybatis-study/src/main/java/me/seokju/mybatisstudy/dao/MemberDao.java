package me.seokju.mybatisstudy.dao;

import me.seokju.mybatisstudy.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    List<Member> findAll();
}
