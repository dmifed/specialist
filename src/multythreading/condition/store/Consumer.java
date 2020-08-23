package multythreading.condition.store;

public class Consumer implements Runnable{
    Store store = Store.initStore();
    @Override
    public void run() {
        for(int i = 0; i < store.GOODS.length; i++){
            try {
                Thread.sleep(6000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            store.get();
        }

    }
}
