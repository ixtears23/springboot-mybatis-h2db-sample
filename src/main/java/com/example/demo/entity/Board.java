package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Board {

    private int id;
    private String content;
}
