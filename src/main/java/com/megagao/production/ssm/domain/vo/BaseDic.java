package com.megagao.production.ssm.domain.vo;

public class BaseDic {
    private Integer dicKey;

    private Integer dicPkey;

    private String dicValue;

    public Integer getDicKey() {
        return dicKey;
    }

    public void setDicKey(Integer dicKey) {
        this.dicKey = dicKey;
    }

    public Integer getDicPkey() {
        return dicPkey;
    }

    public void setDicPkey(Integer dicPkey) {
        this.dicPkey = dicPkey;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }
}