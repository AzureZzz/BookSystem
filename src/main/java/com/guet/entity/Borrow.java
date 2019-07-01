package com.guet.entity;

import java.io.Serializable;
import java.util.Date;

public class Borrow extends BorrowKey implements Serializable {
    private Date retTime;

    private Byte state;

    private Byte reNew;

    private static final long serialVersionUID = 1L;

    public Date getRetTime() {
        return retTime;
    }

    public void setRetTime(Date retTime) {
        this.retTime = retTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getReNew() {
        return reNew;
    }

    public void setReNew(Byte reNew) {
        this.reNew = reNew;
    }
}