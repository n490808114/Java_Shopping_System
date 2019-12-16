package xyz.n490808114.shopWeb.iterator;

import java.util.Iterator;

public class DinerMenu{
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu(){
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT", "(Fakin`) Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05);
    }


    public void addItem(String name, String description,boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if(numberOfItems >= MAX_ITEMS){
            System.out.println("Sorry,menu is full! Can`t add item to menu");
        }else{
            menuItems[numberOfItems] = menuItem;
            numberOfItems ++ ;
        }
    }
    /**
     * @return the menuItems
     */
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    Iterator<MenuItem> iterator(){
        return new InnerIterator(menuItems);
    }

    class InnerIterator implements  Iterator<MenuItem> {
        int index;
        MenuItem[] menuItems;
        public InnerIterator(MenuItem[] menuItems){
            index = 0;
            this.menuItems = menuItems;
        }
        @Override
        public boolean hasNext() {
            return index >= 0 && index < menuItems.length && this.menuItems[index] != null;
        }

        @Override
        public MenuItem next() {
            MenuItem menuItem = this.menuItems[index];
            index ++ ;
            return menuItem;
        }

        
    }
}