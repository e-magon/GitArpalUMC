package centralone;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiThread {
    int portaServer = 21133;
    ServerSocket server;
    Socket client;
    
    /**
     * Avvia il server e attende le connessioni dei client (le quali le gestisce
     * con thread diversi)
     */
    public void attendi() {
        try {
            System.out.println("Server partito");
            server = new ServerSocket(portaServer);
            while (true) {
                client = server.accept();
                new servThread(client).start();
            }
        }        
        catch (Exception e) {
            System.out.println("Errore " + e);
        }
    }
    
    public static void main(String[] args) {
        ServerMultiThread server = new ServerMultiThread();
        server.attendi();
    }
}