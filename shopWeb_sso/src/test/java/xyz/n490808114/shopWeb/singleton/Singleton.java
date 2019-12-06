package xyz.n490808114.shopWeb.singleton;

/**
 * 单例模式
 */
public class Singleton{

    private volatile Singleton uniqueSingleton;

    private Singleton(){}

    public Singleton getInstance(){
        if(uniqueSingleton == null){
            synchronized(Singleton.class){
                if(uniqueSingleton == null){
                    uniqueSingleton = new Singleton();
                }
            }
        }
        return uniqueSingleton;
    }
}