package xyz.n490808114.shopWeb.factory;

import xyz.n490808114.shopWeb.factory.object.NYStyleCheesePizza;
import xyz.n490808114.shopWeb.factory.object.NYStyleClamPizza;
import xyz.n490808114.shopWeb.factory.object.NYStylePepperoniPizza;
import xyz.n490808114.shopWeb.factory.object.NYStyleVeggiePizza;
import xyz.n490808114.shopWeb.factory.object.Pizza;

public class NYPizzaStore extends PizzaStore{

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if("cheese".equals(type)){
            pizza = new NYStyleCheesePizza();
        }else if("veggie".equals(type)){
            pizza = new NYStyleVeggiePizza();
        }else if("clam".equals(type)){
            pizza = new NYStyleClamPizza();
        }else if("pepperoni".equals(type)){
            pizza = new NYStylePepperoniPizza();
        }
        return pizza;
    }
}