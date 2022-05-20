package test.model;

import java.io.Serializable;
import javax.annotation.Generated;

public class User implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String truename;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String department;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String phone;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String company;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String role;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String if_del;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String if_admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String ifdel;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer sort;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private byte[] password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUsername() {
        return username;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTruename() {
        return truename;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartment() {
        return department;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPhone() {
        return phone;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEmail() {
        return email;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCompany() {
        return company;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRole() {
        return role;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIf_del() {
        return if_del;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIf_del(String if_del) {
        this.if_del = if_del == null ? null : if_del.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIf_admin() {
        return if_admin;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIf_admin(String if_admin) {
        this.if_admin = if_admin == null ? null : if_admin.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIfdel() {
        return ifdel;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIfdel(String ifdel) {
        this.ifdel = ifdel == null ? null : ifdel.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getToken() {
        return token;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getSort() {
        return sort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public byte[] getPassword() {
        return password;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPassword(byte[] password) {
        this.password = password;
    }
}