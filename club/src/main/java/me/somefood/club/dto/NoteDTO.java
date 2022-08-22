package me.somefood.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private Long num;

    private String title;

    private String content;

    private String writerEmail;

    private LocalDateTime regDate, modDate;
}
