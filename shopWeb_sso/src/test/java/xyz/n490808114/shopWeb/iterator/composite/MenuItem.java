package xyz.n490808114.shopWeb.iterator.composite;

public class MenuItem extends  MenuComponent{
    String name;
    String description;
    double price;
    boolean isVegetarian;
    public MenuItem(String name,String description,double price,boolean isVegetarian){
        this.name = name;
        this.description = description;
        this.price = price;
        this.isVegetarian = isVegetarian;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        printOne(this);
    }
    @Override
    public double getPrice(){
        return price;
    };
    @Override
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    };

}