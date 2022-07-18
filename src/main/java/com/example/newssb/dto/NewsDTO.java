package com.example.newssb.dto;

import lombok.Data;

@Data
public class NewsDTO {
    private Long id;
    private String title;
    private String shortdescription;
    private String content;
    private Long categoryId;
    private String categoryCode;
    private String LinkTTS;
    private String htmlPage;
    private String tomTat;
}
