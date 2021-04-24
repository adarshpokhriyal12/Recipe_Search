package com.example.recipe_search;

public class RecipeData {
    String Title,Content,Serving;
    int recipeImg;

    public RecipeData(String title, String content, String serving, int recipeImg) {
        this.Title = title;
        this.Content = content;
        this.Serving = serving;
        this.recipeImg = recipeImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }

    public String getServing() {
        return Serving;
    }

    public int getRecipeImg() {
        return recipeImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setServing(String serving) {
        Serving = serving;
    }

    public void setRecipeImg(int recipeImg) {
        this.recipeImg = recipeImg;
    }
}
