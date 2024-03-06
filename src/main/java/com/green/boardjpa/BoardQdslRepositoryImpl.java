package com.green.boardjpa;

import com.green.boardjpa.model.BoardCmtVo;
import com.green.boardjpa.model.BoardDetailVo;
import com.green.boardjpa.model.BoardSelVo;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static com.green.boardjpa.entity.QBoard.board;
import static com.green.boardjpa.entity.QBoardComment.boardComment;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Slf4j
@RequiredArgsConstructor
public class BoardQdslRepositoryImpl implements BoardQdslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardSelVo> selBoardListQdsl(Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(BoardSelVo.class
                        , board.iboard, board.title, board.contents, board.writer, board.createdAt))
                .from(board)
                .orderBy(board.iboard.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


    }

    @Override
    public BoardDetailVo selBoardQdsl(Long iboard) {
        List<BoardDetailVo> list = jpaQueryFactory
                .from(board)
                .leftJoin(board.boardCommentList,boardComment)
                .where(board.iboard.eq(iboard)) //없으면 여러개가 들어갈 수 있음
                .transform( //결과를 변환
                        groupBy(board.iboard) //결과를 묶음
                        .list(Projections.constructor //리턴타입
                                (BoardDetailVo.class,board.iboard, board.title, board.contents, board.writer, board.createdAt
                        , list(Projections.constructor
                                (BoardCmtVo.class, boardComment.iboardComment, boardComment.comment, boardComment.writer, boardComment.createdAt)
                        )))
                );

        return list.size() > 0 ? list.get(0) : null;
    }

}
