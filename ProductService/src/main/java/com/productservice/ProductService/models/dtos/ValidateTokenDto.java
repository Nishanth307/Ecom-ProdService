package com.productservice.ProductService.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    @JsonProperty("token")
    private String token;
    @JsonProperty("userId")
    private Long userId ;

    public ValidateTokenDto(){}
    public ValidateTokenDto(String token,Long userId) {
        this.userId = userId;
        this.token = token;
    }

}
