package pt.ipp.estg.cliente;

import java.io.IOException;

public class InputClass extends Thread {

    String recTextBox;

    public InputClass(String textBox) {
        this.recTextBox = textBox;
    }

    // Create a new Thread to transfer data to socket class
    @Override
    public void run() {
        QueueClass.addMessage(recTextBox);

        try {
            sleep(10);  // milliseconds
        } catch (InterruptedException ex) {}

    }



}
