package com.green.boardjpa.entity;

import com.green.boardjpa.entity.model.BoardSelVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQdslRepository {
    List<BoardSelVo> selBoardListQdsl(Pageable pageable);
}
