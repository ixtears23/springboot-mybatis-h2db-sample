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

    @Override
    public Board findBoardInXMl(int id) {
        log.info("findBoardInXMl - id : {}", id);
        return boardMapper.findById(id);
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
    public void updateBoard(Board board) {
        log.info("updateBoard - board : {}", board);
        boardMapper.updateBoard(board);
        if (FORCE_EXCEPTION_WHETHER) throw new RuntimeException();
    }

}
