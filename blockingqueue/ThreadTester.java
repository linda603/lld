package producerconsumer;

public class ThreadTester {
    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(5);

        new Thread(() -> {
            int count = 0;
            while (count < 10) {
                System.out.println("Pushed " + queue.add(count));
                count += 1;
            }
        }, "Producer").start();

        new Thread(() -> {
            int count = 0;
            while (count < 10) {
                System.out.println("Popped " + queue.remove());
                count += 1;
            }
        }, "Consumer").start();

        System.out.println("Main thread is existing.");
    }
}