package com.somefood.board.domain.like;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.user.Account;
import com.somefood.board.web.dto.LikeCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByBoard(Board board);

    @Query("SELECT COUNT(l.status) FROM Likes as l WHERE l.board=:board GROUP BY l.status")
    List<Long> findLikeDislikeCountPrintLong(@Param("board") Board board);

    @Query("SELECT new com.somefood.board.web.dto.LikeCountDto(l.status, COUNT(l.status)) " +
            "FROM Likes as l WHERE l.board=:board " +
            "GROUP BY l.status " +
            "ORDER BY l.status DESC")
    List<LikeCountDto> findLikeDislikeCount(@Param("board") Board board);
}
