package com.productservice.ProductService.models.datamodels;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String sortParamName;
    private String sortType; //ASC or DESC
}
