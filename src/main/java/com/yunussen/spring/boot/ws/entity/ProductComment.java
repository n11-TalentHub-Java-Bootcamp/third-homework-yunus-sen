package com.yunussen.spring.boot.ws.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "URUN_YORUM"
)
public class ProductComment {
    @SequenceGenerator(name = "generator", sequenceName = "URUN_YORUM_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 500,columnDefinition="TEXT", name = "YORUM")
    private String yorum;

    @Column(name = "YORUM_TARIHI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yorumTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_URUN",
            foreignKey = @ForeignKey(name = "FK_YORUM_URUN_ID")
    )
    private Urun urun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI",
            foreignKey = @ForeignKey(name = "FK_YORUM_KULLANICI_ID")
    )
    private User Kullanici;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public Date getYorumTarihi() {
        return yorumTarihi;
    }

    public void setYorumTarihi(Date yorumTarihi) {
        this.yorumTarihi = yorumTarihi;
    }

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public User getKullanici() {
        return Kullanici;
    }

    public void setKullanici(User kullanici) {
        Kullanici = kullanici;
    }
}
