package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mappers.AnnotationBoardMapper;
import com.example.demo.mappers.XMLBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BoardComponent implements BoardService {
    private static final Boolean FORCE_EXCEPTION_WHETHER = Boolean.TRUE;
    private final AnnotationBoardMapper boardMapperAnnotation;
    private final XMLBoardMapper boardMapper;

    @Override
    public Board findBoardInAnnotation(int id) {
        log.info("findBoardInAnnotation - id : {}", id);
        return boardMapperAnnotation.findById(id);
    }

    /**
     * 시나리오
     * log.info에 debug 포인트를 걸고,
     * h2-console에서 Commit하지 않고 Update 했을 경우 정말 Commit하지 않은 정보를 읽어오는지 확인
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Board findBoardInXMl(int id) {
        log.info("findBoardInXMl - id : {}", id);
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        final Board board = boardMapper.findById(id);
        log.info("board ::: {}", board);
        return board;
    }

    @Override
    public void insertBoard(String content) {
        log.info("insertBoard - content : {}", content);
        boardMapper.insertBoard(content);
    }

    /**
     * Component 에도 Transactional 이 동작하는지 확인
     * Exception을 발생 시켜서 rollback이 되는지 확인
     * @param board
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBoard(Board board) {
        log.info("updateBoard - board : {}", board);
        boardMapper.updateBoard(board);
        if (FORCE_EXCEPTION_WHETHER) throw new RuntimeException();
    }

}
