

public class MyThread extends Thread {
    private String name;

    public MyThread(String name) {   // constructor
        this.name = name;
    }

    // Override the run() method to specify the thread's running behavior
    @Override
    public void run() {
        for (int i = 1; i <= 5; ++i) {
            System.out.println(name + ": " + i);
            yield();
        }
    }
}