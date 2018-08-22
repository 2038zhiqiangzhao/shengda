package com.megagao.production.ssm.domain.vo;

import java.util.Date;

public class BaseSystem {
    private Integer sysParaId;

    private Date createDate;

    private Date modifyDate;

    private String name;

    private String code;

    private String value;

    private Boolean isDelete;

    public Integer getSysParaId() {
        return sysParaId;
    }

    public void setSysParaId(Integer sysParaId) {
        this.sysParaId = sysParaId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}