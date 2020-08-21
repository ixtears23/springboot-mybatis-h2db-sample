package com.example.demo;

import com.example.demo.entity.Board;
import com.example.demo.mappers.AnnotationBoardMapper;
import com.example.demo.mappers.XMLBoardMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mappers")
public class DemoApplication implements ApplicationRunner {

	private final AnnotationBoardMapper boardMapperAnnotation;
	private final XMLBoardMapper xmlBoardMapper;

	public DemoApplication(AnnotationBoardMapper boardMapperAnnotation, XMLBoardMapper xmlBoardMapper) {
		this.boardMapperAnnotation = boardMapperAnnotation;
		this.xmlBoardMapper = xmlBoardMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Board board1 = boardMapperAnnotation.findById(1);
		Board board2 = xmlBoardMapper.findById(2);
		System.out.println(String.format("게시판ID: %s\t내용: %s", board1.getId(), board1.getContent()));
		System.out.println(String.format("게시판ID: %s\t내용: %s", board2.getId(), board2.getContent()));
	}
}
