package producerconsumer;

import java.util.Queue;
import java.util.LinkedList;

public class BlockingQueue {
    private Queue<Integer> queue;
    private int capacity;

    public BlockingQueue(int cap) {
        queue = new LinkedList<>();
        this.capacity = cap;
    }

    public int add(int val) {
        synchronized(queue) {
            // while loop to prevent multiple threads awake from notifyAll but few are able to pass 
            // the condition
            while (queue.size() == capacity) { 
                // wait other threads remove front val from the queue
                try {
                    queue.wait();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            queue.add(val);
            // notify all threads in wait state
            queue.notifyAll();
            return val;
        }
    }

    public int remove() {
        synchronized(queue) {
            while (queue.size() == 0) {
                // wait other threads adding val to the queue
                try {
                    queue.wait();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            int val = queue.poll();
            // notify all threads in wait state
            queue.notifyAll();
            return val;
        }
    }
}