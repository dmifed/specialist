package multythreading.condition.store;

public class TestCondition {
    Store store;

    public TestCondition() {
        store = Store.initStore();

        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();

        while (producer.isAlive() || consumer.isAlive()){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Finish working");
        System.exit(0);
    }

    public static void main(String[] args) {
        new TestCondition();
    }


}
