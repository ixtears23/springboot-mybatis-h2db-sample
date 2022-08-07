package com.example.demo.mappers;

import com.example.demo.entity.Board;

public interface XMLBoardMapper {
    Board findById(int id);
    void insertBoard(String content);
    void updateBoard(Board board);
}
