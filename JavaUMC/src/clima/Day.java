/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clima;

import java.util.Random;

/**
 *
 * @author 5ib10
 */
public class Day {    
    private Season season;
    private final Random weather;
    
    private float hydrometricLevel;
    private float snowHeight;
    private float rainfall;
    private float temperature;
    private float relativeHumidity;
    private float sunRadiations;
    private float windSpeed;
    private WindDirection windDirection;
    
    private boolean fog;
    private boolean rain;
    
    /**
     *
     * @param season
     */
    public Day(Season season) {
        this.season = season;
        weather = new Random();
        
        generateWeather();
    }
    
    /**
     *
     */
    public void generateWeather() {
        temperature = season.getAverageTemp().range() * weather.nextFloat() + season.getAverageTemp().min();
        rain = weather.nextFloat() <= season.getRainProb();
        
        if(rain) {
            if(temperature > 0) {
                rainfall = season.getAverageRain() / 31 + weather.nextFloat() * 1 - (float)0.5;
                hydrometricLevel = (float) (weather.nextFloat() * 0.3 + 3.7 + rainfall);
            } else {
                snowHeight = season.getAverageRain() / 31 + weather.nextFloat() * 1 - (float)0.5;
                hydrometricLevel = (float) (weather.nextFloat() * 0.3 + 3.7 + rainfall);
            }
            
            sunRadiations = weather.nextFloat() * 4 + 48;
            relativeHumidity = weather.nextFloat() * 2 + 98;
        } else 
            fog = weather.nextFloat() <= season.getFogProb();
        
        if(fog) {
            sunRadiations = weather.nextFloat() * 200 + 400;
            relativeHumidity = weather.nextFloat() * 2 + 98;
        } else {
            sunRadiations = weather.nextFloat() * 200 + 900;
            relativeHumidity = season.getAverageHumidity() * 100 + (weather.nextFloat() * 10 - 5);
        }
        
        windSpeed = weather.nextFloat() * 3 + 1;
        windDirection = WindDirection.values()[Math.abs(weather.nextInt()) % 16];        
    }

    /**
     *
     * @return
     */
    public float getHydrometricLevel() {
        return hydrometricLevel;
    }

    /**
     *
     * @return
     */
    public float getSnowHeight() {
        return snowHeight;
    }

    /**
     *
     * @return
     */
    public float getRainfall() {
        return rainfall;
    }

    /**
     *
     * @return
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     *
     * @return
     */
    public float getRelativeHumidity() {
        return relativeHumidity;
    }

    /**
     *
     * @return
     */
    public float getSunRadiations() {
        return sunRadiations;
    }

    /**
     *
     * @return
     */
    public float getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @return
     */
    public WindDirection getWindDirection() {
        return windDirection;
    }
    
    /**
     *
     * @return
     */
    public Season getSeason() {
        return season;
    }
    
    /**
     *
     * @param season
     */
    public void setSeason(Season season) {
        this.season = season;
    }
}
