package multythreading.condition.store;

public class Producer implements Runnable{
    Store store = Store.initStore();
    @Override
    public void run() {
        for(int i = 0; i < store.GOODS.length; i++){
            store.put(store.GOODS[i]);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
