package com.yunussen.spring.boot.ws.model;

import javax.validation.constraints.NotBlank;

public class UserDeleteRequestModel {

    @NotBlank
    private String kullaniciAdi;
    @NotBlank
    private String telefon;

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
