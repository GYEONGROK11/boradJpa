package com.green.boardjpa.entity;

import com.green.boardjpa.entity.model.BoardCommentInsDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
//클라이언트 사이드 렌더링 : 서버에서 데이터(json)만 전달하고 클라이언트에서 렌더링 //리액트 사용할때
//@Controller
//서버 사이드 렌더링 : 서버에서 데이터와 렌더링까지 모두 처리
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;

    @PostMapping
    public Long postBoard(@RequestBody Board board){
        return service.postBoard(board);
    }

    @DeleteMapping
    public Long deleteBoard(@RequestParam Long iboard){
        service.deleteBoard(iboard);
        return 1L;
    }

    @PostMapping("/cmt")
    public Long postBoardComment(@RequestBody BoardCommentInsDto boardComment){
        return service.postBoardComment(boardComment);
    }


}
