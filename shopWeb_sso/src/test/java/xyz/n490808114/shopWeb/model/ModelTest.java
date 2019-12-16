package xyz.n490808114.shopWeb.model;


public class ModelTest{
    public static void main(String[] args) {
        CaffeineBeverage[] drinks = new CaffeineBeverage[10];
        drinks[0] = new Coffee();
        drinks[1] = new Tea();
        for(CaffeineBeverage drink : drinks){
            drink.prepareRecipe();
        }
    }

}