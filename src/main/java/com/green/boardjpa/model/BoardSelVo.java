package com.green.boardjpa.model;

import com.green.boardjpa.entity.BoardComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

public class BoardSelVo {
    private Long iboard;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
    //private List<BoardComment> boardComments = new ArrayList<>();
}
