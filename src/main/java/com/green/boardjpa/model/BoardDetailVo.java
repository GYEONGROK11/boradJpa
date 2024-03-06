package com.green.boardjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardDetailVo {
    private Long iboard;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdAt;

    private List<BoardCmtVo> cmtList = new ArrayList<>();
}
