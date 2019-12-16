package xyz.n490808114.shopWeb.model;

public class Coffee extends CaffeineBeverage {
    private String name;
    public Coffee(){
        name = "Coffee";
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }
    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}