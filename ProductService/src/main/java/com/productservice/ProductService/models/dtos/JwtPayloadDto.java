package com.productservice.ProductService.models.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtPayloadDto {
    @JsonProperty("createdAt")
    private long createdAt;
    @JsonProperty("expiredAt")
    private long expiredAt;
    @JsonProperty("roles")
    private List<String> roles;
    @JsonProperty("userId")
    private Long userId;
}