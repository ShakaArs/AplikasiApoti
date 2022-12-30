package com.example.aplikasiapoti;


public class UserHealth {
    private int id;
    private String kategoriPos;
    private int tinggiBadan;
    private int beratBadan;
    private int lingkarLengan;
    private String namapasien;
    private String tensiDarah;
    private String url;

    public UserHealth() {
        this.kategoriPos = kategoriPos;
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
        this.lingkarLengan = lingkarLengan;
        this.namapasien = this.namapasien;
        this.tensiDarah = tensiDarah;
        this.url = this.url;
    }

    public UserHealth(int id, String kategoriPos, int tinggiBadan, int beratBadan, int lingkarLengan, String namapasien, String tensiDarah, String url) {
        this.id = id;
        this.kategoriPos = kategoriPos;
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
        this.lingkarLengan = lingkarLengan;
        this.namapasien = namapasien;
        this.tensiDarah = tensiDarah;
        this.url = url;
    }

    public UserHealth(int id, String kategoripos, int tinggibadan, int beratbadan, String lingkarlengan, String tensidarah, String namapasien, String url) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategoriPos() {
        return kategoriPos;
    }

    public void setKategoriPos(String kategoriPos) {
        this.kategoriPos = kategoriPos;
    }

    public int getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(int tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public int getLingkarLengan() {
        return lingkarLengan;
    }

    public void setLingkarLengan(int lingkarLengan) {
        this.lingkarLengan = lingkarLengan;
    }

    public String getNamapasien() {
        return namapasien;
    }

    public void setNamapasien(String namapasien) {
        this.namapasien = namapasien;
    }

    public String getTensiDarah() {
        return tensiDarah;
    }

    public void setTensiDarah(String tensiDarah) {
        this.tensiDarah = tensiDarah;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
