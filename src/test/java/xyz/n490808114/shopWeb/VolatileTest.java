package xyz.n490808114.shopWeb;

public class VolatileTest {
    public static void main(String[] args) {
        Resources resources = new Resources();
        Runnable subThread1 = new SubThread(resources);
        new Thread(subThread1, "减法线程1").start();
        new Thread(subThread1, "减法线程2").start();
    }
}

class Resources {
    public int num = 100;

    public synchronized void add() {
        this.num++;
    }

    public void sub() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num--;
    }
}

class AddThread implements Runnable {
    private Resources resources;

    public AddThread(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            this.resources.add();
        }

    }
}

class SubThread implements Runnable {
    private Resources resources;

    public SubThread(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        while (this.resources.num > 0) {
            this.resources.sub();
            System.out.println(Thread.currentThread().getName() + "执行!  num = " + this.resources.num);
        }

    }
    
}