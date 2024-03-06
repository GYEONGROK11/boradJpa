package com.green.boardjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.boardjpa.entity.Board;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BoardCommentInsDto {
    @JsonIgnore
    private Long iboardComment;

    private Long iboard;

    private String comment;

    private String writer;

    private String pw;
}
