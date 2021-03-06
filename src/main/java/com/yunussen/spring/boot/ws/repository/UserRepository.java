package com.yunussen.spring.boot.ws.repository;

import com.yunussen.spring.boot.ws.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
