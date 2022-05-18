package com.somefood.board.domain.board;

import com.somefood.board.domain.BaseTimeEntity;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.like.LikeStatus;
import com.somefood.board.domain.like.Likes;
import com.somefood.board.domain.user.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "boards")
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Lob
    private String content;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Account writer;

    @OneToMany(mappedBy = "board")
    @ToString.Exclude
    private List<Likes> likes = new ArrayList<>();

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setCategory(Category category) {
        if (this.category != null) { // 기존에 이미 팀이 존재한다면
            this.category.getBoards().remove(this); // 관계를 끊는다.
        }
        this.category = category;
        category.getBoards().add(this);
    }

    public void setWriter(Account account) {
        this.writer = account;
//        account.getBoards().add(this);
    }

    @Builder
    public Board(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getLikeCount() {
        int likeCount = 0;
        int dislikeCount = 0;
        for (Likes like : likes) {
            if (like.getStatus() == LikeStatus.LIKE) {
                likeCount++;
            } else {
                dislikeCount++;
            }
        }
        return likeCount - dislikeCount;
    }
}


