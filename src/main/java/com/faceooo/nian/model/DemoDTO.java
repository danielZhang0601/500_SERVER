package com.faceooo.nian.model;

/**
 * Created by qin on 15/8/19.
 */
public class DemoDTO {

    public int id;
    public String uname;
    public String pwd;
    public String phone;
    public String nick;

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPhone() {
        return phone;
    }

    public String getNick() {
        return nick;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
