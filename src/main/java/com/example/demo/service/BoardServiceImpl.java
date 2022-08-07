package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mappers.AnnotationBoardMapper;
import com.example.demo.mappers.XMLBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

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

    @Override
    public void updateBoard(Board board) {
        log.info("updateBoard - board : {}", board);
        boardMapper.updateBoard(board);
    }

}
