package com.productservice.ProductService.models.datamodels;

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
      @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
      private UUID id;
      
      @Column(name = "created_at", updatable = false)
      private java.time.LocalDateTime createdAt;
      
      @Column(name = "last_updated_at")
      private java.time.LocalDateTime lastUpdatedAt;
      
      @PrePersist
      protected void onCreate() {
          createdAt = java.time.LocalDateTime.now();
          lastUpdatedAt = java.time.LocalDateTime.now();
      }
      
      @PreUpdate
      protected void onUpdate() {
          lastUpdatedAt = java.time.LocalDateTime.now();
      }
}
//@GenericGenerator(name = "generator_name", strategy = "uuid2") depricated
