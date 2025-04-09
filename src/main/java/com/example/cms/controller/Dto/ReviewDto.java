package com.example.cms.controller.Dto;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ReviewDto {
    private String userId;
    private Long productId;
    private String reviewBody;
    private Integer score;
    private String date;
}
