package com.green.boardjpa.entity.model;

import lombok.Data;

@Data
public class BoardUpdDto {
    private Long iboard;
    private String pw;
    private String title;
    private String contents;
}
