package com.nhnacademy.servlet;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<BuyList.Item> foods = new ArrayList<>();

    public void add(BuyList.Item food) {
        foods.add(food);
    }

    public ArrayList<BuyList.Item> getFoods() {
        return foods;
    }
}
