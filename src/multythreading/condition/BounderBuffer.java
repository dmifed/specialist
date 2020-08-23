package multythreading.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BounderBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[25];
    int putPointer;
    int takePointer;
    int count;

    public void put(Object x){
        lock.lock();
        try {
            while (count == items.length){ //buffer is full. can't put. wait
                notFull.await();
            }
            items[putPointer] = x;
            if(++putPointer == items.length){
                putPointer = 0;
            }
            ++count;
            notEmpty.signal();
            //System.out.printf("Thread %s has put an object %s and signal notEmpty\n", Thread.currentThread().getName(), x.toString());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //System.out.printf("Thread %s unlock\n", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public Object take(){
        lock.lock();
        Object x;
        try {
            while (count == 0){ //buffer is empty. can't take. wait
                notEmpty.await();
            }
            x = items[takePointer];
            if(++takePointer == items.length){
                takePointer = 0;
            }
            --count;
            notFull.signal();
            //System.out.printf("Thread %s has take an object %s and signal notFull\n", Thread.currentThread().getName(), x.toString());
            return x;
        }catch (InterruptedException e){
            e.printStackTrace();
            return null;
        }finally {
            //System.out.printf("Thread %s unlock\n", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BounderBuffer b = new BounderBuffer();
        for(int i = 0; i < 5; i++){
            new Thread(() -> {
                while (true){
                    Object x = new Object();
                    b.put(x);
                    System.out.println("put: " + x);
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for(int i = 0; i < 5; i++){
            new Thread(() -> {
                while (true){
                    Object x = b.take();
                    System.out.println("\ttake: " + x);
                    try {
                        Thread.sleep(4000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
