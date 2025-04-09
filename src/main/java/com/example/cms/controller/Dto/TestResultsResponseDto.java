package com.example.cms.controller.Dto;
import com.example.cms.controller.Dto.ProductDto;

import lombok.Getter;
import lombok.Setter;
import com.example.cms.controller.Dto.ProductDto;
import java.util.List;

@Getter
@Setter
public class TestResultsResponseDto {
    private Long testResultId;
    private Float budget;
    private String skinTypeName;
    private List<ProductDto> recommendedProducts;
    private List<String> avoidIngredientsNames;
    private List<String> concernNames;
    private List<String> categoryNames;

}
