package com.somefood.board.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CategoryType {

    NOTICE("공지사항"),
    FREE("자유게시판"),
    QUESTION("질문게시판");

    private final String title;
}
