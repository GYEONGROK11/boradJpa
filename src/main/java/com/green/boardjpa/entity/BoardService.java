package com.green.boardjpa.entity;

import com.green.boardjpa.entity.model.BoardCommentInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
