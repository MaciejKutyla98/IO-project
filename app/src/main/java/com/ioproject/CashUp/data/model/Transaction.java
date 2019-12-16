package com.ioproject.CashUp.data.model;

public class Transaction {
    private String transactionId;
    private String category;
    private String price;
    private String description;
    private String date;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
