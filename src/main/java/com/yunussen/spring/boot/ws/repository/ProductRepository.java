package com.yunussen.spring.boot.ws.repository;

import com.yunussen.spring.boot.ws.dto.ProductDetailDto;
import com.yunussen.spring.boot.ws.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByCategoryId(String categoryId);

    ProductDetailDto findProductDetailDtoById(String id);
}
