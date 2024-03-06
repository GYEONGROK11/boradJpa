package com.green.boardjpa;

import com.green.boardjpa.model.BoardDetailVo;
import com.green.boardjpa.model.BoardSelVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQdslRepository {
    List<BoardSelVo> selBoardListQdsl(Pageable pageable);
    BoardDetailVo selBoardQdsl(Long iboard);
}
