package xyz.n490808114.shopWeb.adapter;

public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
    @Override
    public void fly() {
        System.out.println("I`m flying");
    }
}