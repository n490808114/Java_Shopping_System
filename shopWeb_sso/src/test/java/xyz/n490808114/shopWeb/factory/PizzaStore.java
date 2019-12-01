package xyz.n490808114.shopWeb.factory;

import xyz.n490808114.shopWeb.factory.object.Pizza;

public abstract class PizzaStore{

    final Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
    abstract Pizza createPizza(String type);
}