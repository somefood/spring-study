package com.somefood.board.web.dto;

import com.somefood.board.domain.like.LikeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LikeCountDto {

    private LikeStatus likeStatus;
    private Long count;

    public LikeCountDto(LikeStatus likeStatus, Long count) {
        this.likeStatus = likeStatus;
        this.count = count;
    }
}
