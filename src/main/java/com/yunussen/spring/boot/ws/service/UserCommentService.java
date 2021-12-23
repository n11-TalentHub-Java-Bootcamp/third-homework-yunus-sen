package com.yunussen.spring.boot.ws.service;

import com.yunussen.spring.boot.ws.entity.ProductComment;
import com.yunussen.spring.boot.ws.repository.ProductCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserCommentService {
    @Autowired
    private ProductCommentRepository commetRepository;

    public List<ProductComment> findAll(){
        return commetRepository.findAll();
    }

    public ProductComment findById(String id){

        Optional<ProductComment> optional = commetRepository.findById(id);

        ProductComment comment = null;
        if (optional.isPresent()){
            comment = optional.get();
        }

        return comment;
    }

    public void deleteById(String id){
        commetRepository.deleteById(id);
    }

    public ProductComment save(ProductComment comment){
        return commetRepository.save(comment);
    }

}
