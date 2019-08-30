package com.example.boot.mybatis.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    @Id
    private String id;

    /**
     * 用户名，登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     * 0：女
     * 1：男
     * 2：保密
     */
    private Integer sex;

    /**
     * 职业类型：
     * 1：Java开发
     * 2：前端开发
     * 3：大数据开发
     * 4：ios开发
     * 5：Android开发
     * 6：Linux系统工程师
     * 7：PHP开发
     * 8：.net开发
     * 9：C/C++
     * 10：学生
     * 11：其它
     */
    private Integer job;

    /**
     * 头像地址
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 用于权限的“盐”
     */
    @Column(name = "auth_salt")
    private String authSalt;

    /**
     * 最后一次登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "regist_time")
    private Date registTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthSalt() {
        return authSalt;
    }

    public void setAuthSalt(String authSalt) {
        this.authSalt = authSalt;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}