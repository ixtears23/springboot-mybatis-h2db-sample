package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mappers.AnnotationBoardMapper;
import com.example.demo.mappers.XMLBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private static final Boolean FORCE_EXCEPTION_WHETHER = Boolean.TRUE;
    private final BoardComponent boardComponent;
    private final AnnotationBoardMapper boardMapperAnnotation;
    private final XMLBoardMapper boardMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public Board findBoardInAnnotation(int id) {
        log.info("findBoardInAnnotation - id : {}", id);
        final Board board1 = boardMapperAnnotation.findById(id);
        log.info("REPEATABLE_READ ::: {}", board1);
// 서로 다른 트랜잭션 REPEATABLE_READ 테스트
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException exception) {
//            log.error(exception.getMessage(), exception);
//        }
// 동일 트랜잭션 REPEATABLE_READ 테스트
//        boardMapper.updateBoard(
//                Board.builder()
//                        .id(id)
//                        .content("REPEATABLE_READ")
//                        .build()
//        );
        final Board board2 = boardMapperAnnotation.findById(id);
        log.info("REPEATABLE_READ ::: {}", board2);
        log.info("findBoardInAnnotation - id : {}", id);
        return boardMapperAnnotation.findById(id);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    @Override
    public Board findBoardInXMl(int id) {
        log.info("findBoardInXMl - id : {}", id);
        boardMapper.findById(id);
        return boardMapper.findById(id);
    }

    @Override
    public void insertBoard(String content) {
        log.info("insertBoard - content : {}", content);
        boardMapper.insertBoard(content);
    }

    @Override
    public void updateBoard(Board board) {
        log.info("updateBoard - board : {}", board);
        boardMapper.updateBoard(board);
    }

    @Transactional(rollbackFor = Exception.class)
    public void throwExceptionToUpdateBoard(Board board) {
        log.info("updateBoard - board : {}", board);
        boardMapper.updateBoard(board);
        if (FORCE_EXCEPTION_WHETHER) throw new RuntimeException();
    }

}
