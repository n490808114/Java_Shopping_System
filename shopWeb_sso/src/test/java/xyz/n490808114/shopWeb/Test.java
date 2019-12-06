package xyz.n490808114.shopWeb;

public class Test{
    public static void main(String[] args) {
        int[] arr = {1,3,5};
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
        String a = "123";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("1");
        stringBuffer.append("2");
        stringBuffer.append("3");
        System.out.println(a.contentEquals(stringBuffer));

    }
    private static void change(int[] arr){
        arr[0] = 200;
    }
}