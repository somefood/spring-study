package com.somefood.board.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BasicResponseDto<T> {

    private int code;
    private String message;

    private T extra;

    @Builder
    public BasicResponseDto(int code, String message, T extra) {
        this.code = code;
        this.message = message;
        this.extra = extra;
    }
}
