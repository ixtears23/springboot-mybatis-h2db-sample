package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mappers.AnnotationBoardMapper;
import com.example.demo.mappers.XMLBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    /*
        왜 @Autowired를 안썼는데 주입이 될까?
        Spring 4.3에서는 주입받고자 하는 멤버에 대한 생성자를 만들면
        자동으로 주입해 줌.
     */
    private final AnnotationBoardMapper boardMapperAnnotation;
    private final XMLBoardMapper xmlBoardMapper;

    private final Logger logger = LoggerFactory.getLogger(BoardService.class);

    public BoardService(AnnotationBoardMapper boardMapperAnnotation, XMLBoardMapper xmlBoardMapper) {
        this.boardMapperAnnotation = boardMapperAnnotation;
        this.xmlBoardMapper = xmlBoardMapper;
    }

    public Board findBoardInAnnotation(int id) {
        logger.info("findBoardInAnnotation");
        return boardMapperAnnotation.findById(id);
    }

    public Board findBoardInXMl(int id) {
        System.out.println("GOOD");
        logger.debug("로거레벌을 INFO로 해놨기 때문에 해당 로그는 찍히지 않음.findBoardInXMl");
        return xmlBoardMapper.findById(id);
    }
}
