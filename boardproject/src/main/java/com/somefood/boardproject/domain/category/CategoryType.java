package com.somefood.boardproject.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    NOTICE("notice", "공지"),
    QUESTION("question", "질문"),
    FREE("free", "자유");

    private final String type;
    private final String korean;
}
