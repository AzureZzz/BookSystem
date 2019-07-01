package com.guet.entity;

import java.io.Serializable;
import java.util.Date;

public class Reserve extends ReserveKey implements Serializable {
    private Date resTime;

    private Reader reader;

    private Book book;

    private static final long serialVersionUID = 1L;

    public Date getResTime() {
        return resTime;
    }

    public void setResTime(Date resTime) {
        this.resTime = resTime;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}