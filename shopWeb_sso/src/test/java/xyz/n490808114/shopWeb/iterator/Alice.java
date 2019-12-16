package xyz.n490808114.shopWeb.iterator;

import java.util.Iterator;

public class Alice {
    PancakeHouseMenu pancakeHouseMenu;
    DinerMenu dinerMenu;

    public Alice(PancakeHouseMenu pancakeHouseMenu,DinerMenu dinerMenu){
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;

    }
    void printMenu(){
        print(pancakeHouseMenu.iterator());
        print(dinerMenu.iterator());
    }
    void print(Iterator<MenuItem> iterator){
        while(iterator.hasNext()){
            MenuItem menuItem = iterator.next();
            System.out.print(menuItem.getName() + "\t<<");
            System.out.print(menuItem.getPrice() + ">>\t");
            System.out.println(menuItem.getDescription());
        }
    }
    void printBreakfastMenu(){

    }
    void printLunchMenu(){

    }
    void printVegetarianMenu(){

    }
    boolean isItemVegetarian(String name){
        return false;
    }
}