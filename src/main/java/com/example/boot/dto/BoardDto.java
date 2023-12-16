package com.example.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private int id;
    private String title;
    private String content;
    private String writer;
}
