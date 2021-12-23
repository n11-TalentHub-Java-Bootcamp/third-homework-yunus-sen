package com.yunussen.spring.boot.ws.dao;

import com.yunussen.spring.boot.ws.entity.Urun;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrunDao extends CrudRepository<Urun, Long> {

    @Query("select urun from Urun urun where urun.kategori.id = :kategoriId")
    List<Urun> findAllByKategoriOrderByIdDesc(Long kategoriId);

}