package level2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock extends Thread {
    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            while (!lock.tryLock(1, TimeUnit.MILLISECONDS)){
                System.out.println("Wait " + Thread.currentThread().getName() + "...................");
            }
            try {
                for(int i = 1; i <= 100; i++){
                    System.out.printf("%s - %d\n", this.getName(), i);
                }
            }finally {
                lock.unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new TestLock();
        Thread t2 = new TestLock();
        Thread t3 = new TestLock();
        t1.start();
        t2.start();
        t3.start();
    }
}
