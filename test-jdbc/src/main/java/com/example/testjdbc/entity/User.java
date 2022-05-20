package com.example.testjdbc.entity;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {
    private Integer id;

    private String username;

    private String truename;

    private String department;

    private String phone;

    private String email;

    private String company;

    private String role;

    private String if_del;

    private String if_admin;

    private String ifdel;

    private String token;

    private Integer sort;

    private byte[] password;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getIf_del() {
        return if_del;
    }

    public void setIf_del(String if_del) {
        this.if_del = if_del == null ? null : if_del.trim();
    }

    public String getIf_admin() {
        return if_admin;
    }

    public void setIf_admin(String if_admin) {
        this.if_admin = if_admin == null ? null : if_admin.trim();
    }

    public String getIfdel() {
        return ifdel;
    }

    public void setIfdel(String ifdel) {
        this.ifdel = ifdel == null ? null : ifdel.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", truename='" + truename + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", role='" + role + '\'' +
                ", if_del='" + if_del + '\'' +
                ", if_admin='" + if_admin + '\'' +
                ", ifdel='" + ifdel + '\'' +
                ", token='" + token + '\'' +
                ", sort=" + sort +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}