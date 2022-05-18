package com.somefood.board.domain.like;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LikeStatus {

    LIKE("좋아요", "like", "#afa"),
    DISLIKE("싫어요", "dislike", "#faa");

    private final String korea;
    private final String english;
    private final String color;
}
