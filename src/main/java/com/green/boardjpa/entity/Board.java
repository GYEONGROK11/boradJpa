package com.green.boardjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity //엔티티 클래스임을 명시
public class Board extends BaseEntity{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인클리먼트
    @Column(columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private Long iboard;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String contents;

    @Column(length = 10,nullable = false)
    private String writer;

    @Column(length = 100,nullable = false)
    private String pw;

    //양방향 설정
    @JsonIgnore
    @ToString.Exclude //string으로 변환시 얘는 제외하고 변환 (무한루프)
    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE, orphanRemoval = true)
    //Lazy는 프록시 객체 (셀렉할때만 가져옴)
    //eager는 프록시 객체가 아닌 실제 객체를 가져옴 - 쿼리는 한번에 날리지만 부분적으로 가져올 수 없어서 부담이 많이됨
    //댓글이 적을때는 EAGER로 해도 상관없지만 댓글이 많아지면 부담이 많이됨
    //cascade = CascadeType.PERSIST : 영속성 전이, 부모객체를 저장할 때 자식객체도 함께 저장
    //댓글은 게시글에 종속적이기 때문에 게시글이 삭제되면 댓글도 삭제되어야 하므로 CascadeType.REMOVE를 추가
    //orphanRemoval = true : 부모객체와 연관관계가 끊어지면 자식객체를 자동으로 삭제
    private List<BoardComment> boardCommentList = new ArrayList<>();
}
