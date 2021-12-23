package com.yunussen.spring.boot.ws.repository;

import com.yunussen.spring.boot.ws.entity.ProductComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepository extends MongoRepository<ProductComment, String> {
}
