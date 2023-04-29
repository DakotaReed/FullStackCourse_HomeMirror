package chatGPT;

public class MyThread extends Thread {
    public void run() {
        System.out.println("Hello from thread, id: " + Thread.currentThread().getId());
        System.out.println("Hello from thread, name: " + Thread.currentThread().getName());
        System.out.println("Hello from thread: " + Thread.currentThread().toString());
        System.out.println("Hello from thread, group: " + Thread.currentThread().getThreadGroup());
        System.out.println("Hello from thread, priority: " + Thread.currentThread().getPriority());
    }

    public void setName() {
        Thread.currentThread().setName("First");
        System.out.println("Hello from thread, changed name: " + Thread.currentThread().getName());
    }

}
