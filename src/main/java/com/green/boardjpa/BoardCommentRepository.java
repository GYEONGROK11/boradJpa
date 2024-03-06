package com.green.boardjpa;

import com.green.boardjpa.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long>{
    @Modifying
    @Query("delete from BoardComment bc where bc.board.iboard = :iboard")
    void deleteByBoard(Long iboard);


    @Query("select bc from BoardComment bc where bc.board.iboard = :iboard")
    List<BoardComment> searchAllByBoard(Long iboard);

}
