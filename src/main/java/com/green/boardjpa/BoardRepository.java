package com.green.boardjpa;

import com.green.boardjpa.entity.Board;
import com.green.boardjpa.model.BoardSelVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>, BoardQdslRepository{
    // JpaRepository를 상속받아서 기본적인 CRUD를 사용할 수 있음
    // JpaRepository<Entity,PK타입>
    // repository를 생성하면 자동으로 구현체가 생성됨
    List<Board> findAllByOrderByIboardDesc(Pageable pageable);

    @Query("select b from Board b order by b.iboard desc")//Query만 적으면 셀렉트라고 인식 Modefy를 적어야됨
    List<Board> selBoardList(Pageable pageable);

    @Query("select new com.green.boardjpa.model.BoardSelVo(b.iboard, b.title, b.contents, b.writer, b.createdAt) " +
            "from Board b order by b.iboard desc")
    List<BoardSelVo> selBoardList2(Pageable pageable);

}
