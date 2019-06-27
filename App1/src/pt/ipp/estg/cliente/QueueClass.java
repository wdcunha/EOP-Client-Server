package pt.ipp.estg.cliente;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueClass  {

    String recTextBox;

    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public static void addMessage(String message) {

        System.out.println("Queue added!");

        queue.add(message);

        System.out.println("queue: "+queue);

        SenderConverter senderConverter = new SenderConverter();
        senderConverter.start();
    }

    public static String getNextMessage() {
        return queue.poll();
    }
}
