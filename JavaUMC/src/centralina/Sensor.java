package centralina;

import clima.Day;
import clima.Season;


public class Sensor {
    private final String measurementUnit;
    private final String type;
    
    
    public Sensor(String measurementUnit, String type) {
        this.measurementUnit = measurementUnit;
        this.type = type;
    }
    
    public float measure(Day day) {
        switch(type) {
            case "Termometro":
                return day.getTemperature();
                
            case "Igrometro":
                return day.getRelativeHumidity();
                
            case "Pluviometro":
                return day.getSeason() == Season.WINTER ? day.getSnowHeight() : day.getRainfall();
                
            case "Idrometro":
                return day.getHydrometricLevel();
                
            case "Solarimetro":
                return day.getSunRadiations();
            
            case "Anemoscopio":
                return day.getWindDirection().ordinal();
                
            case "Anemometro":
                return day.getWindSpeed();
        }
        
        return (float) 0.0;
    }
    
    public String getType() {
        return type;
    }
    
    public String getMeasurementUnit() {
        return measurementUnit;
    }
}
