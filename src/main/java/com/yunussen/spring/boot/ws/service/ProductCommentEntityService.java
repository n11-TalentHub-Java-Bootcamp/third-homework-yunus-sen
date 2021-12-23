package com.yunussen.spring.boot.ws.service;

import com.yunussen.spring.boot.ws.dao.ProductCommentDao;
import com.yunussen.spring.boot.ws.dao.UserDao;
import com.yunussen.spring.boot.ws.dto.CommentUserDto;
import com.yunussen.spring.boot.ws.dto.ProductCommentDto;
import com.yunussen.spring.boot.ws.entity.ProductComment;
import com.yunussen.spring.boot.ws.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCommentEntityService {

    @Autowired
    private ProductCommentDao commentDao;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProductCommentDto> getAllByUserId(Long id) {
        List<ProductComment> comments= (List<ProductComment>) commentDao.findAllByUserId(id);
        if(comments.size()==0)throw new IllegalArgumentException("kullanıcının yorumu bulunmuyor.");
        List<ProductCommentDto> returnValue=new ArrayList<>();
        comments.forEach(comment->returnValue.add(modelMapper.map(comment,ProductCommentDto.class)));
        return returnValue;
    }
    public List<ProductCommentDto> getAllByProductId(Long id) {
        List<ProductComment> comments= (List<ProductComment>) commentDao.findAllByProductId(id);
        if(comments.size()==0)throw new IllegalArgumentException("urune ait yorum bulunmuyor.");
        List<ProductCommentDto> returnValue=new ArrayList<>();
        comments.forEach(comment->returnValue.add(modelMapper.map(comment,ProductCommentDto.class)));
        return returnValue;
    }

    public ProductCommentDto save(ProductCommentDto model){
        return modelMapper.map(commentDao.save(modelMapper.map(model,ProductComment.class)),ProductCommentDto.class);
    }

    public void delete(Long id){
        commentDao.deleteById(id);
    }

}
