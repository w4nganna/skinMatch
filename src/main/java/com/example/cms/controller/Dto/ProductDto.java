package com.example.cms.controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String brand;
    private Double price;
    private String imageURL;
}
