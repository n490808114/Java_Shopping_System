package xyz.n490808114.shopWeb.model;

public class Tea extends CaffeineBeverage{
    private String name;
    public Tea(){
        this.name = "Tea";
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }
    @Override
    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}