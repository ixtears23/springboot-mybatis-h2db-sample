package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/{id}")
    public @ResponseBody Board findBoardInAnnotation(@PathVariable("id") int id) {
        return boardServiceImpl.findBoardInAnnotation(id);
    }

    @GetMapping
    public ResponseEntity<Board> findBoardInXML(@RequestParam int id) {
        return new ResponseEntity<>(boardServiceImpl.findBoardInXMl(id), HttpStatus.OK);
    }

    @PutMapping
    public void createBoard(@RequestBody Board board) {
        boardServiceImpl.insertBoard(board.getContent());
    }

    @PatchMapping
    public void updateBoard(@RequestBody Board board) {
        boardServiceImpl.updateBoard(board);
    }

}
