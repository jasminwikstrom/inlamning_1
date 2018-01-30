package model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private BigDecimal storlek;
    private String farg;
    private int categoryId;
    private String categoryName;


    public Product(int id, String name, BigDecimal storlek, String farg, int categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.storlek = storlek;
        this.farg = farg;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStorlek() {
        return storlek;
    }

    public void setStorlek(BigDecimal storlek) {
        this.storlek = storlek;
    }

    public String getFarg() {
        return farg;
    }

    public void setFarg(String farg) {
        this.farg = farg;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", storlek=" + storlek +
                ", farg='" + farg + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}