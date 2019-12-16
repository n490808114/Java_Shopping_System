package xyz.n490808114.shopWeb.iterator.composite;

import java.util.ArrayList;
import java.util.List;


public class Menu extends MenuComponent{
    List<MenuItem> list;
    String name;
    String description;
    public Menu(String name,String description){
        list = new ArrayList<>();
        this.name = name;
        this.description = description;
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
        printList(list.iterator());
    }
    @Override
    public void add(MenuItem component) {
        list.add(component);

    }

    @Override
    public boolean remove(MenuItem component) {
        return list.remove(component);
    }

    @Override
    public MenuItem getChild(int i) {
        return list.get(i);
    }
}