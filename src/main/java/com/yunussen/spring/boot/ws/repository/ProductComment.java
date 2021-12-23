package com.yunussen.spring.boot.ws.repository;

import com.yunussen.spring.boot.ws.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductComment extends MongoRepository<ProductComment, String> {
}
