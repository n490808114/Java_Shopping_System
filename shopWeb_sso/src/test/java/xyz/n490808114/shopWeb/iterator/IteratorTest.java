package xyz.n490808114.shopWeb.iterator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        System.out.println(Duration.between(LocalDateTime.now(), LocalDate.now().plusDays(1L).atTime(0,0,0)).toMillis());
        System.out.println(getTime());
    }
    public static long getTime(){
        return (86400000L * ((System.currentTimeMillis() + 28800000L) / 86400000L)) + 57600000L - System.currentTimeMillis();
    }
    public static void testFor(){
        List<Integer> inters = new ArrayList<Integer>(){
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
            add(1);add(2);add(3);add(4);add(5);
            add(6);add(7);add(8);add(9);add(10);
        }};
        int length = inters.size();
        for (int i = 0; i < length; i++) {
            System.out.println("=================" +length);
            System.out.println(i + " : >>>" + inters.get(i));
            if(i ==4 ){inters.remove(4);length --;}
        }
    }
}