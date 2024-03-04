package com.green.boardjpa.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long>{
    @Modifying
    @Query("delete from BoardComment bc where bc.board.iboard = :iboard")
    void deleteByBoard(Long iboard);

}
