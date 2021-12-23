package com.yunussen.spring.boot.ws.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.util.Date;

@Document(collection = "product_comment")
public class ProductComment {
    @Id
    private String id;
    private String yorum;
    private Date yorumTarihi;
    private String productId;
    private String UserId;
}
