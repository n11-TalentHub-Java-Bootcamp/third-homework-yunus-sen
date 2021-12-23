package com.yunussen.spring.boot.ws.dao;

import com.yunussen.spring.boot.ws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    Optional<User> findByKullaniciAdi(String kullaniciAdi);
    Optional<User> findByTelefon(String telefon);
    User save(User user);
}
