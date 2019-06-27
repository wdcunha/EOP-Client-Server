package pt.ipp.estg.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SenderConverter extends Thread {

    private Socket cliente;
    Scanner entrada;

    private void initCliente(){
        try {
            cliente = new Socket("127.0.0.1",3322);
        } catch (IOException ex) {
            Logger.getLogger(UIMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Create a new Thread to transfer data to socket class
    @Override
    public void run() {
        Timer timer = new Timer("Thread Sender_Converter", true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String message = QueueClass.getNextMessage();

                if(message != null) {
                    initCliente();

                    if(cliente != null) {
                        sender(message);
                        try {
                            receiver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        sender("Server connexion lost!");
                    }
                }
            }
        }, 0, 1);
    }

    public void sender(String textBox){
        try {
            System.out.println("textBox: "+textBox);
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            saida.println(converter(textBox));
        } catch (IOException ex) {
            Logger.getLogger(SenderConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiver() throws IOException {
        entrada = new Scanner(cliente.getInputStream());
        String entradaToString;
        while(entrada.hasNextLine()){
            entradaToString = entrada.nextLine();
            System.out.println(entradaToString);
        }
    }


    public static StringBuilder converter(String textBox) {
        // convert textbox content to hexa
        String str = textBox;
        char ch[] = str.toCharArray();
        StringBuilder sbHexa = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            sbHexa.append(Integer.toHexString((int) ch[i]));
        }
        return sbHexa;
    }
}
