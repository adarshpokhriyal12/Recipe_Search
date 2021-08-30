package com.example.recipe_search;

public class IngredientsData {
    String name;
    //int ingr_image;
    public IngredientsData(String name) {
        this.name = name;
        //this.quantity = quantity;
        //this.ingr_image = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  /*  public int getIngr_image() {
        return ingr_image;
    }

    public void setIngr_image(int ingr_image) {
        this.ingr_image = ingr_image;
    }



    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }*/
}
