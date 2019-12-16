package xyz.n490808114.shopWeb.juc;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class LockTest{
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        List<Integer> list = new ArrayList<>(10);
        list.add(2);
        list.add(3);

        System.out.println(list);

        List<String> list2 = list.stream().map(each -> each == null ? "insert" : "update").collect(Collectors.toList());
        System.out.println(list2);
        new HashMap<>().put("key", "");
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date);
        System.out.println(dateString);
    }
}