package xyz.n490808114.shopWeb.iocTest;


public class Subject{
    @TryAutoWired("new other")
    Other other;

    @TryAutoWired
    Another another;

    public static void main(String[] args) {
        
    }
}