package pt.ipp.estg.server;

public class PrinterMain {

    public static void main(String args[]){

        Thread[] threads =  { new PrinterThread() };

        for (Thread t : threads){
            t.start();
        }
    }
}
