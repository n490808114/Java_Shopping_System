package xyz.n490808114.shopWeb.adapter;

import java.util.LinkedList;
import java.util.List;

public class AdapterTest {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        DuckAdapter fackTurkey = new DuckAdapter(mallardDuck);
        Turkey wildTurkey = new WildTurkey();
        TurkeyAdapter fackDuck = new TurkeyAdapter(wildTurkey);

        List<Duck> ducks = new LinkedList<>();

        ducks.add(mallardDuck);
        ducks.add(fackDuck);

        List<Turkey> turkeys = new LinkedList<>();

        turkeys.add(wildTurkey);
        turkeys.add(fackTurkey);
        System.out.println("\n========================= Duck ==========================\n");
        for (Duck duck : ducks) {
            duck.quack();
            duck.fly();
        }
        System.out.println("\n======================== Turkey =========================\n");
        for (Turkey turkey : turkeys) {
            turkey.gobble();
            turkey.fly();
        }

    }
}