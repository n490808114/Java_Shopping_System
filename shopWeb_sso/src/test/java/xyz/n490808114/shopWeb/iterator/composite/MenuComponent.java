package xyz.n490808114.shopWeb.iterator.composite;

import java.util.Iterator;

public abstract class MenuComponent {
    public String getName(){throw new UnsupportedOperationException();};
    public String getDescription(){throw new UnsupportedOperationException();};
    public double getPrice(){throw new UnsupportedOperationException();};
    public boolean isVegetarian(){throw new UnsupportedOperationException();};
    public void print(){throw new UnsupportedOperationException();};
    public void add(MenuItem Component){throw new UnsupportedOperationException();};
    public boolean remove(MenuItem component){throw new UnsupportedOperationException();};
    public MenuItem getChild(int i){throw new UnsupportedOperationException();};

    protected void printOne(MenuItem menuItem){
        System.out.println(menuItem.getName() + "\t>>>\t" + menuItem.getPrice() + "\t>>>\t" + menuItem.getDescription());
    }
    protected void printList(Iterator<MenuItem> iterator){
        while(iterator.hasNext()){
            printOne(iterator.next());
        }
    }
}