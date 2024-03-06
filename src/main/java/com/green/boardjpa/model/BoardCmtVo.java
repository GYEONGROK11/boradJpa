package com.green.boardjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardCmtVo {
    private Long iboardComment;
    private String comment;
    private String writer;
    private LocalDateTime createdAt;
}
