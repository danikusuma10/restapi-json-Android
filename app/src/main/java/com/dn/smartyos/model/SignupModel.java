package com.dn.smartyos.model;

public class SignupModel {
    private String username;
    private String fullname;
    private String password;
    private String email;
    private String sekolah;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String location) {
        this.sekolah = sekolah;
    }
}
