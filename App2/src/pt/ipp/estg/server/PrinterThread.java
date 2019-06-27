package pt.ipp.estg.server;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrinterThread extends Thread {

    ServerSocket server;
    Socket cliente;
    Scanner entrada;
    Date date = new Date();
    SimpleDateFormat formatter;

    private void initCliente(){
        try {
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            server = new ServerSocket(3322);
            System.out.println(formatter.format(date)+" - [Server started at Port 3322]");

            cliente = server.accept();
            System.out.println(formatter.format(date)+" - Client connected from IP " + cliente.getInetAddress().
                    getHostAddress());

            receiver();
            sender();

            entrada.close();
            server.close();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        initCliente();

    }

    public void sender(){
        try {
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            saida.println(formatter.format(date)+" - Client message printed successfully on Server!");
        } catch (IOException ex) {
            Logger.getLogger(PrinterThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiver() throws IOException {
        entrada = new Scanner(cliente.getInputStream());
        String entradaToString;
        while(entrada.hasNextLine()){
            entradaToString = entrada.nextLine();
            System.out.println("Hexa format: "+entradaToString);
            System.out.println("hexToString: "+hexToString(entradaToString));
        }
    }

    public static String hexToString(String hex){

        StringBuilder finalString = new StringBuilder();
        StringBuilder tempString = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            finalString.append((char)decimal);
            tempString.append(decimal);
        }
        return finalString.toString();
    }
}
