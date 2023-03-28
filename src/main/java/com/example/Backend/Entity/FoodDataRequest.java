package com.example.Backend.Entity;


public class FoodDataRequest {
    public int foodID;
    public String foodName;
    public String img;
    public float price;
    public Category cat ;
    public boolean available;





    public FoodDataRequest( String foodName, String img, float price, Category cat, boolean availability) {

        this.foodName = foodName;
        this.img=img;
        this.price = price;
        this.cat = cat;
        this.available = availability;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
