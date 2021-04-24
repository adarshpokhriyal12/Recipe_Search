package com.example.recipe_search;

public class IngredientsData {
    String name, quantity ;
    int ingr_image;
    public IngredientsData(String name, String quantity,int img) {
        this.name = name;
        this.quantity = quantity;
        this.ingr_image = img;
    }

    public int getIngr_image() {
        return ingr_image;
    }

    public void setIngr_image(int ingr_image) {
        this.ingr_image = ingr_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
