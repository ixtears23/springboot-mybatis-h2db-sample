package com.example.demo.service;

import com.example.demo.entity.Board;

public interface BoardService {
    Board findBoardInAnnotation(int id);
    Board findBoardInXMl(int id);
    void insertBoard(String content);
    void updateBoard(Board board);
}
