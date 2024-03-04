package com.green.boardjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity //엔티티 클래스임을 명시
    public class BoardComment extends BaseEntity{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인클리먼트
    @Column(columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private Long iboardComment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "iboard",nullable = false)
    private Board board;

    @Column(length = 300,nullable = false)
    private String comment;

    @Column(length = 10,nullable = false)
    private String writer;

    @Column(length = 100,nullable = false)
    private String pw;
}
