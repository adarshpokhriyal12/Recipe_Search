package com.example.recipe_search;

public class SliderItem {

    // image urls can be stored here using string variavles
    private int image;
    private String name;

    SliderItem(int image,String name)
    {
        this.image = image;
        this.name = name;
    }

    public int getImage()
    {
        return image;
    }

    public String getName() {
        return name;
    }
}
