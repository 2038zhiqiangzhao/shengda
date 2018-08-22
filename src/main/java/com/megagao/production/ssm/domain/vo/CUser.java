package com.megagao.production.ssm.domain.vo;

import java.util.Date;

import com.megagao.production.ssm.domain.dto.BaseToken;

public class CUser extends BaseToken{
    private Long id;

    private String name;

    private String age;

    private Short sex;

    private Date birthday;

    private String userphoto;

    private String salt;

    private String password;

    private String introduce;

    private String nickName;

    private String mobile;

    private Short thirdWayLogin;

    private String thirdPlatformCode;

    private Short apptype;

    private String openid;

    private Long deviceid;

    private String email;

    private String wechat;

    private Byte memberLevel;

    private Byte isLocked;

    private Byte isDelete;

    private Byte isAvailable;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String createIp;

    private Byte status;

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

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto == null ? null : userphoto.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Short getThirdWayLogin() {
        return thirdWayLogin;
    }

    public void setThirdWayLogin(Short thirdWayLogin) {
        this.thirdWayLogin = thirdWayLogin;
    }

    public String getThirdPlatformCode() {
        return thirdPlatformCode;
    }

    public void setThirdPlatformCode(String thirdPlatformCode) {
        this.thirdPlatformCode = thirdPlatformCode == null ? null : thirdPlatformCode.trim();
    }

    public Short getApptype() {
        return apptype;
    }

    public void setApptype(Short apptype) {
        this.apptype = apptype;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public Byte getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Byte memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Byte getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Byte isLocked) {
        this.isLocked = isLocked;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Byte isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}