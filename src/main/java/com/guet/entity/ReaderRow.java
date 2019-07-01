package com.guet.entity;

public class ReaderRow {
    private String username;

    private String name;

    private Byte type;

    private String email;

    private Double balance;

    private int borrowNum;

    private int unReturn;

    private int overNum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    public int getUnReturn() {
        return unReturn;
    }

    public void setUnReturn(int unReturn) {
        this.unReturn = unReturn;
    }

    public int getOverNum() {
        return overNum;
    }

    public void setOverNum(int overNum) {
        this.overNum = overNum;
    }
}
