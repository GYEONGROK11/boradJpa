package com.green.boardjpa.entity;

import com.green.boardjpa.entity.model.BoardCommentInsDto;
import com.green.boardjpa.entity.model.BoardSelVo;
import com.green.boardjpa.entity.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    public Long postBoard(Board board){
        //save메소드는 insert와 update를 모두 처리
        String hashedPw = BCrypt.hashpw(board.getPw(), BCrypt.gensalt());
        board.setPw(hashedPw);
        boardRepository.save(board);

        return board.getIboard();
    }

    public Long postBoardComment(BoardCommentInsDto dto){
        //Board board = new Board();
        //board.setIboard(dto.getIboard());
        Board board2 = boardRepository.getReferenceById((dto.getIboard()));
        //둘중 아무거나 위는 영속성 없는것 밑은 영속성 있는것

        String hashedPw = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());

        BoardComment boardComment = new BoardComment();
        //boardComment.setBoard(board);
        boardComment.setBoard(board2);
        boardComment.setComment(dto.getComment());
        boardComment.setWriter(dto.getWriter());
        boardComment.setPw(hashedPw);

        boardCommentRepository.save(boardComment);

        return boardComment.getIboardComment();
    }

    public void deleteBoard(Long iboard){
        Board board = boardRepository.getReferenceById(iboard);
        boardCommentRepository.deleteByBoard(iboard);

        boardRepository.delete(board); //deleteById(iboard)도 가능

    }

    @Transactional
    public Long putBoard(BoardUpdDto dto){
        Board board = boardRepository.getReferenceById(dto.getIboard());
        if(!BCrypt.checkpw(dto.getPw(),board.getPw())){
            return -1L;
        }
        board.setTitle(dto.getTitle()); //영속성으로인해 자동 업데이트
        board.setContents(dto.getContents());

        return board.getIboard();

    }


    public List<BoardSelVo> getBoardList(Pageable pageable){

        //1 Default 쿼리 메소드
        /* Page<Board> list1 = boardRepository.findAll(pageable);

        List<BoardSelVo> result = new ArrayList<>();

        for (Board item : list1.getContent()) {
            result.add(BoardSelVo.builder()
                    .iboard(item.getIboard())
                    .title(item.getTitle())
                    .writer(item.getWriter())
                    .contents(item.getContents())
                    .createdAt(item.getCreatedAt())
                    .build());
        }
        return result;*/

        //람다식이란 펑션 인터페이스를 임플리먼트 해 객체생성을 하는것

        /*List<BoardSelVo> result2 =  list1.get().map(item->
             BoardSelVo.builder()
                    .iboard(item.getIboard())
                    .title(item.getTitle())
                    .writer(item.getWriter())
                    .contents(item.getContents())
                    .createdAt(item.getCreatedAt())
                    .build()
        ).collect(Collectors.toList());

        return result2;*/

        //2 Custom 쿼리 메소드
        /*List<Board> list2 = boardRepository.findAllByOrderByIboardDesc(pageable);

        List<BoardSelVo> result3 =  list2.stream().map(item->
                BoardSelVo.builder()
                        .iboard(item.getIboard())
                        .title(item.getTitle())
                        .writer(item.getWriter())
                        .contents(item.getContents())
                        .createdAt(item.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());

        return result3;*/

        //3 JPQL
        /*List<Board> list3 = boardRepository.selBoardList(pageable);

        List<BoardSelVo> result4 =  list3.stream().map(item->
                BoardSelVo.builder()
                        .iboard(item.getIboard())
                        .title(item.getTitle())
                        .writer(item.getWriter())
                        .contents(item.getContents())
                        .createdAt(item.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());

        return result4;*/

        //4 QueryDSL

        return  boardRepository.selBoardListQdsl(pageable);

    }
}
