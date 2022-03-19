package com.somefood.boardproject.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {

    NOTICE("공지"),
    QUESTION("질문"),
    FREE("자유");

    private final String type;
}
