package xyz.n490808114.shopWeb;

public class Test{
    public static void main(String[] args) {
        Long count = 6L;
        System.out.println(count == null || count <= 5);
    }

}

interface A{
    default void a(){
        System.out.println("1");
    };
}
interface B{
    default void a(){
        System.out.println("2");
    };
}
class C implements A,B{
    public void a() {
        A.super.a();
    }
}
