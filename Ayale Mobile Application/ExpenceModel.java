package com.example.savindu.ayalenew1;

public class ExpenceModel {

    private int id;
    private String amount;
    private String category;
    private String description;

    public ExpenceModel(int id, String amount, String category, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public ExpenceModel(String amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
