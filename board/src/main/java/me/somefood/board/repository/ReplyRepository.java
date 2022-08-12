package me.somefood.board.repository;

import me.somefood.board.entity.Board;
import me.somefood.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying // JPQL에서 update, delete 하려면 필요한 어노테이션
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(@Param("bno") Long bno);

    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
