package xyz.n490808114.shopWeb.model;

import java.util.Arrays;
import java.util.Collections;

public abstract class CaffeineBeverage{
    
    public final void prepareRecipe(){
        System.out.println("========== " + getName() + " ==========");
        boilWater();
        brew();
        pourInCup();
        addCondiments();
        System.out.println("==============================");
        Object[] objects = new Object[10];
        objects.clone();
        Arrays.sort(objects);
        Collections.sort(null);
        Class<?> clazz = CaffeineBeverage.class;
        
     }
    abstract String getName();

    abstract void brew();

    abstract void addCondiments();

    public void boilWater() {
        System.out.println("Boiling water");
    }
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }
}