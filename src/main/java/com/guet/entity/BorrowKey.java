package com.guet.entity;

import java.io.Serializable;
import java.util.Date;

public class BorrowKey implements Serializable {
    private String username;

    private String bookNo;

    private Date borTime;

    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo == null ? null : bookNo.trim();
    }

    public Date getBorTime() {
        return borTime;
    }

    public void setBorTime(Date borTime) {
        this.borTime = borTime;
    }
}