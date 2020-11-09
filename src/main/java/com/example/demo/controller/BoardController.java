package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    private final BoardService boardService;

    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * http://localhost:8888/board/2
     * Header
     *   Content-Type: application/json
     */
    @GetMapping("/board/{id}")
    public @ResponseBody Board findBoardInAnnotation(@PathVariable("id") int id) {
        return boardService.findBoardInAnnotation(id);
    }

    /**
     * http://localhost:8888/board
     * Header
     *   Content-Type: application/x-www-form-urlencoded
     * Body
     *   key: id
     *   value: 2
     */
    @PostMapping("/board")
    public ResponseEntity<Board> findBoardInXML(@RequestParam int id) {
        return new ResponseEntity<Board>(boardService.findBoardInXMl(id), HttpStatus.OK);
    }
}
