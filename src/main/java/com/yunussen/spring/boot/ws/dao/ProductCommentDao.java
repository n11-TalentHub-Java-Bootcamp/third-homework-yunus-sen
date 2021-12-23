package com.yunussen.spring.boot.ws.dao;

import com.yunussen.spring.boot.ws.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentDao extends JpaRepository<ProductComment, Long> {

    @Query("select p from ProductComment p where p.Kullanici.id= ?1")
    Iterable<ProductComment> findAllByUserId(Long userId);

    @Query("select p from ProductComment p where p.urun.id= ?1")
    Iterable<ProductComment> findAllByProductId(Long productId);
}
