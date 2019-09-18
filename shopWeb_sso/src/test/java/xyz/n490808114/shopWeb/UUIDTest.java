package xyz.n490808114.shopWeb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDTest{
    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(s.length());
        
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}