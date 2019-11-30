package xyz.n490808114.shopWeb.factory;

import xyz.n490808114.shopWeb.factory.object.Pizza;

public class PizzaMaker{
    private SimplePizzaFactory factory;

    public PizzaMaker(SimplePizzaFactory factory){
        this.factory = factory;
    }

    Pizza orderPizza(String type){
        Pizza pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}