package level2.condition.store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    static Store store = null;

    Lock lock;
    Condition condition;
    SimpleDateFormat date;
    final String[] GOODS = {"Milk", "Sugar", "Beer", "Tea", "Coffee"};
    List<String> goods;

    private Store() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        date =new SimpleDateFormat("HH:mm:ss");
        goods = new ArrayList<>();
    }

    public static Store initStore(){
        synchronized (Store.class){
            if(store == null){
                store = new Store();
            }
            return store;
        }
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void get(){
        lock.lock();
        try {
            //Waiting at empty store
            while (goods.size() < 1){
                condition.await();
            }
            printMessage("Realization from store: " + goods.get(0));
            goods.remove(0);
            printMessage();

            //Signal
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void put(String good){
        lock.lock();
        try {
            //Waiting for free seat
            while (goods.size() >= 3){
                condition.await();
            }
            goods.add(good);
            printMessage("Delivery to store: " + good);
            printMessage();

            //Signal
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printMessage(String message){
        System.out.println(date.format(new Date()) + " ---- " + message);
    }

    public void printMessage(){
        System.out.println("\tGoods in store: " + goods.size());
    }




}
