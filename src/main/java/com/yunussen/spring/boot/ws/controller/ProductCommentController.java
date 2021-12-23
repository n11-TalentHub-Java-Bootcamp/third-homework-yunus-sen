package com.yunussen.spring.boot.ws.controller;

import com.yunussen.spring.boot.ws.entity.ProductComment;
import com.yunussen.spring.boot.ws.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-comments/")
public class ProductCommentController {
    /**
     * http://localhost:8080/api/product-comments/
     */
    @Autowired
    private ProductCommentService commentService;

    /**
     * /
     * @return
     */
    @GetMapping("")
    public List<ProductComment> findAll() {
        return commentService.findAll();
    }

    /**
     * /id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ProductComment findById(@PathVariable String id) {
        return commentService.findById(id);
    }

    /**
     * /
     * @param comment
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody ProductComment comment) {

        comment = commentService.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    /**
     * id
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        commentService.deleteById(id);
    }

}