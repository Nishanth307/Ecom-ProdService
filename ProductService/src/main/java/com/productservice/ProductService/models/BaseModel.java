package com.productservice.ProductService.models;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseModel{
      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      //@GenericGenerator(name = "generator_name", strategy = "uuid2") depricated
      @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
      private UUID id;
}
