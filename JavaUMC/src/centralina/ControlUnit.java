package centralina;

import clima.Day;
import clima.Season;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class ControlUnit {
    private final Day day;
    private final ArrayList <SensorMemory> sensors;
    Socket server;
    PrintStream ps;
    int ID;
    float coordinatesN, coordinatesE;
    static final String host = "localhost";
    static final int port = 21133;
    
    /**
     *
     * @param season
     * @param ID
     * @param coordinatesN
     * @param coordinatesE
     */
    public ControlUnit(Season season, int ID, float coordinatesN, float coordinatesE) {
        day = new Day(season);
        sensors = new ArrayList<>();
        populateSensorList();
        this.ID = ID;
        this.coordinatesN = coordinatesN;
        this.coordinatesE = coordinatesE;
    }

    /**
     * 
     */
    private void populateSensorList() {
        sensors.add(new SensorMemory(new Sensor("cm", "Idrometro"), 12));
        sensors.add(new SensorMemory(new Sensor("mm", "Pluviometro"), 12));
        sensors.add(new SensorMemory(new Sensor("°C", "Termometro"), 12));
        sensors.add(new SensorMemory(new Sensor("%", "Igrometro"), 12));
        sensors.add(new SensorMemory(new Sensor("W/mq", "Solarimetro"), 12));
        sensors.add(new SensorMemory(new Sensor("m//s", "Anemometro"), 12));
        sensors.add(new SensorMemory(new Sensor("°", "Anemoscopio"), 12));
    }
    
    /**
     * 
     * @param sensor
     * @return 
     */
    private float getMeasure(Sensor sensor) {
        return sensor.measure(day);
    }
    
    /*
    private float average(float[] measurements) {
    }
    */

    /**
     * Connette la centralina al server centrale (centralone)
     * @param host L'indirizzo del centralone
     * @param port La porta di ascolto del centralone
     * @return L'esito della connessione
     */    
    public boolean connect(String host, int port) {
        try {
            server = new Socket(host, port);
            ps = new PrintStream(server.getOutputStream());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * Utilizzando il protocollo IS-UMC, la centralina invia le misurazioni
     * con PUSH al centralone
     * @param sensorID ID del sensore che ha effettuato la misurazione
     * @param measurements Le misurazioni
     */
    private void sendMeasurements(int sensorID, float[] measurements) {
        ps.println("PUSH " + ID + " " + coordinatesN + " " + coordinatesE + " "
                + System.currentTimeMillis() / 1000L + " " + sensorID + " "
                + Arrays.toString(measurements));
    }
    
    private void timerize() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                float data;
                for (SensorMemory sm : sensors) {
                    data = getMeasure(sm.getSensor());
                    if(!sm.memorize(data)) {
                        sendMeasurements(sensors.indexOf(sm), sm.readMemory());
                        sm.resetMemory();
                        sm.memorize(data);
                    }
                }
                
                day.generateWeather();
            }
        }, 0, 1000);
    }
    
    /**
     * Chiude la connessione con il server
     */
    private void closeConnection() {
        try {
            server.close();
            ps.close();
        } catch (IOException ex) {}
    }
    
    public static void main(String[] args) {
        ControlUnit cu = new ControlUnit(Season.SPRING, 10, (float)4.3221, (float)5.89854);
        if(cu.connect(host, port)){
            cu.timerize();
        } else
            System.out.println("Errore nella connessione");
    }
}