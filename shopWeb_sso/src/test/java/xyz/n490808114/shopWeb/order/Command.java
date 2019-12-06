package xyz.n490808114.shopWeb.order;

public interface Command{
    void execute();
    void undo();
}