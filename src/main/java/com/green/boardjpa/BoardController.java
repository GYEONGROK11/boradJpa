package com.green.boardjpa;

import com.green.boardjpa.entity.Board;
import com.green.boardjpa.model.BoardCommentInsDto;
import com.green.boardjpa.model.BoardDetailVo;
import com.green.boardjpa.model.BoardSelVo;
import com.green.boardjpa.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public Long putBoard(@RequestBody BoardUpdDto dto){
        return service.putBoard(dto);
    }

    @GetMapping("/page")
    public int getTotalPage(Pageable pageable){
        return service.getTotalPage(pageable);
    }

    @GetMapping("/{iboard}")
    public BoardDetailVo getBoard(@PathVariable Long iboard){
        return service.getBoard(iboard);
    }

    @GetMapping
    public List<BoardSelVo> getBoardList(@PageableDefault(size = 4,sort = "iboard",direction = Sort.Direction.DESC) Pageable pageable){
        return service.getBoardList(pageable);
    }

    @PostMapping("/cmt")
    public Long postBoardComment(@RequestBody BoardCommentInsDto boardComment){
        return service.postBoardComment(boardComment);
    }


}
