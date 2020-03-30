package com.dn.smartyos.model;

public class LoginModel {
    private String status;
    public String getStatus(){ return status; }
    private void setStatus(String status){this.status=status;}

    private dataLogin dataLogin;

    public dataLogin getDataLogin() {
        return dataLogin;
    }
    public class dataLogin{
        private int id;
        private String fullname;
        private String username;
        private String password;
        private String email;
        private String location;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getFullname() {
            return fullname;
        }
        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location = location;
        }

        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
    }
}
