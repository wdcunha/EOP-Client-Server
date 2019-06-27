

public class TestMyThread {
    public static void main(String[] args) {
        Thread[] threads = {
                new MyThread("Thread 1"),
                new MyThread("Thread 2"),
                new MyThread("Thread 3")
        };
        for (Thread t : threads) {
            t.start();
        }
    }
}