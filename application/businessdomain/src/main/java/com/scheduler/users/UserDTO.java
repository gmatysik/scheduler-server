package com.scheduler.users;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private long id;
    private String email;
    private String phone;

    public UserDTO(){

    }

    public UserDTO(long id, String email, String phone){
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(UserDTO user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
