package com.yunussen.spring.boot.ws.controller;

import com.yunussen.spring.boot.ws.dto.ProductDetailDto;
import com.yunussen.spring.boot.ws.entity.Product;
import com.yunussen.spring.boot.ws.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public MappingJacksonValue findAllUrunList() {

        List<Product> productList = productService.findAll();

        MappingJacksonValue mapping = new MappingJacksonValue(productList);

        return mapping;

    }

    @GetMapping("/{id}")
    public MappingJacksonValue findUrunById(@PathVariable String id){

        Product product = productService.findById(id);

        WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllUrunList()
        );

        EntityModel entityModel = EntityModel.of(product);

        entityModel.add(linkToUrun.withRel("tum-urunler"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        return mapping;

//        return productService.findById(id);
    }

    @GetMapping("/detail/{id}")
    public Product findProductDetailDtoById(@PathVariable String id){
        return productService.findAllProductByCategoryId(id).get(0);
    }

    @PostMapping("")
    public ResponseEntity<Object> saveUrun(/**@Valid*/ @RequestBody Product product){
        product = productService.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable String id){
        productService.delete(id);
    }

    @GetMapping("kategoriler/{categoryId}")
    public List<Product> findAllUrunByKategoriId(@PathVariable String categoryId){
        return productService.findAllProductByCategoryId(categoryId);
    }
}