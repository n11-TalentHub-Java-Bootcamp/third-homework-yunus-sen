package com.yunussen.spring.boot.ws.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "KULLANICI"
)
public class User {

    @SequenceGenerator(name = "generator", sequenceName = "KULLANICI_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 50, name = "ADI")
    private String adi;

    @Column(length = 50, name = "SOYADI")
    private String soyadi;

    @Column(length = 50, name = "EMAIL",unique = true)
    private String email;

    @Column(length = 20, name = "KULLANICI_ADI",unique = true)
    private String kullaniciAdi;

    @Column(length = 15, name = "TELEFON",unique = true)
    private String telefon;

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}