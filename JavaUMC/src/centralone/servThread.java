package centralone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.*;
import java.util.Calendar;

public class servThread extends Thread {
    BufferedReader inDalClient;
    String stringaRicevuta;
    Socket client;
    
    /**
     * Inizializza la connessione
     * @param socketClient Il client
     */
    public servThread(Socket socketClient) {
        try {
            client = socketClient;
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (Exception e) {
            System.out.println("Errore " + e);
        }
    }
    
    /**
     * Avvia il thread, riceve le stringhe e le appende in un file
     */
    public void run() {
        try {
            stampa("Si è connesso " + client.getInetAddress());
            while(true) {
                stringaRicevuta = inDalClient.readLine();
                stampa(client.getInetAddress() + " ha inviato " + stringaRicevuta);
                scrivi(stringaRicevuta);
            }
        }
        catch (Exception e) {
            stampa("Si è disconnesso " + client.getInetAddress() + "\n");
        }
    }
    
    /**
     * Formatta le stringhe per stamparle meglio
     * @param stringa 
     */
    private void stampa(String stringa) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        System.out.println(hour + "." + minute + ":  " + stringa);
    }
    
    /**
     * Appende sul file la stringa ricevuta. Metodo sincronizzato
     * @param daScrivere La stringa da salvare
     * @throws IOException 
     */
    private synchronized void scrivi(String daScrivere) throws IOException {
        Files.write(Paths.get("ricevuti.txt"), daScrivere.getBytes(), StandardOpenOption.APPEND);
    }
}