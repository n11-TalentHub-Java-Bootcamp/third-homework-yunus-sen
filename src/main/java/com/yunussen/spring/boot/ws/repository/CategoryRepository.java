package com.yunussen.spring.boot.ws.repository;

import com.yunussen.spring.boot.ws.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
