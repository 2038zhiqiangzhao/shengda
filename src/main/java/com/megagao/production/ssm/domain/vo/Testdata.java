package com.megagao.production.ssm.domain.vo;

public class Testdata {
    private Long id;

    private String name;

    private String age;

    private String url;

    private int isDeplyed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public int getIsDeplyed() {
        return isDeplyed;
    }

    public void setIsDeplyed(int isDeplyed) {
        this.isDeplyed = isDeplyed;
    }
}