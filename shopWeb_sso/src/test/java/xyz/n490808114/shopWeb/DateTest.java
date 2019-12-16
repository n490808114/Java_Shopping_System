package xyz.n490808114.shopWeb;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTest {
    public static void main(String[] args) {
        long start = 1573827048000L;
        while (start < 1575885917110L){
            start += 100000000L;
            System.out.println("RIGHT TIME:>>>>>>>>>>>>>>>>>>>>>>>>>>"+new Date(start));
            long a = getNextDay(start);
            System.out.println("NEXT DAY:"+new Date(a + start));
        }
    }
    static long getNextDay(long l){
        return (86400000L * ((l + 28800000) / 86400000L)) + 57600000L - l;
    }
}