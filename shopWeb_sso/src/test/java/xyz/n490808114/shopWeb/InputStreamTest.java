package xyz.n490808114.shopWeb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class InputStreamTest {
    public static void main(String[] args) {
        String test = "this is test for inputStream,test how inpusteam work";
        Character[] list = new Character[test.toCharArray().length];
        for (int i = 0; i < test.toCharArray().length; i++) {
            list[i] = test.toCharArray()[i];
        }
        Arrays.stream(list).forEach(System.out::print);
    }
}
