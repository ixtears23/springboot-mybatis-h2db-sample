package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardService boardService;

    BoardController(@Qualifier("boardServiceImpl") BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public @ResponseBody Board findBoardInAnnotation(@PathVariable("id") int id) {
        return boardService.findBoardInAnnotation(id);
    }

    @GetMapping
    public ResponseEntity<Board> findBoardInXML(@RequestParam int id) {
        return new ResponseEntity<>(boardService.findBoardInXMl(id), HttpStatus.OK);
    }

    @PutMapping
    public void createBoard(@RequestBody Board board) {
        boardService.insertBoard(board.getContent());
    }

    @PatchMapping
    public void updateBoard(@RequestBody Board board) {
        boardService.updateBoard(board);
    }

}
