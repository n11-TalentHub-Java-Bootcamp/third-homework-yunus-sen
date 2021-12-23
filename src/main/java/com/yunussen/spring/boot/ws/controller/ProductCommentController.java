package com.yunussen.spring.boot.ws.controller;

import com.yunussen.spring.boot.ws.dto.CommentUserDto;
import com.yunussen.spring.boot.ws.dto.ProductCommentDto;
import com.yunussen.spring.boot.ws.entity.ProductComment;
import com.yunussen.spring.boot.ws.service.ProductCommentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class ProductCommentController {
    
    @Autowired
    private ProductCommentEntityService commnentService;

    @GetMapping("/kullanici/{userId}")
    public ResponseEntity<List<ProductCommentDto>>getAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(commnentService.getAllByUserId(userId));
    }

    @GetMapping("/urun/{urunId}")
    public ResponseEntity<List<ProductCommentDto>>getAllByProductId(@PathVariable Long urunId){
        return ResponseEntity.ok(commnentService.getAllByProductId(urunId));
    }

    @PostMapping("/")
    public ResponseEntity<ProductCommentDto>getAllByProductId(@RequestBody ProductCommentDto model){
        return ResponseEntity.ok(commnentService.save(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable Long id){
        commnentService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }




}
