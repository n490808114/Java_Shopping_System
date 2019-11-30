package xyz.n490808114.shopWeb.factory;

import xyz.n490808114.shopWeb.factory.object.CheesePizza;
import xyz.n490808114.shopWeb.factory.object.ClamPizza;
import xyz.n490808114.shopWeb.factory.object.GreekPizza;
import xyz.n490808114.shopWeb.factory.object.Pizza;
import xyz.n490808114.shopWeb.factory.object.VeggiePizza;

public class SimplePizzaFactory {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if("cheese".equals(type)){
            pizza = new CheesePizza();
        }else if ("greek".equals(type)){
            pizza = new GreekPizza();
        }else if ("clam".equals(type)){
            pizza = new ClamPizza();
        }else if ("veggie".equals(type)){
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}