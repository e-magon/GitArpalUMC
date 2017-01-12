/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.ssss
 */
package clima;

/**
 *
 * @author 5ib10
 */
public enum Season {

    /**
     *
     */
    SPRING((float)85.0, new Range((float) 7.6, (float)17.6), (float)0.62, (float)0.0, (float)0.5),

    /**
     *
     */
    SUMMER((float)76.6, new Range((float)17.0, (float)28.0), (float)0.32, (float)0.0, (float)0.7),

    /**
     *
     */
    FALL  ((float)90.0, new Range((float) 9.0, (float)14.6), (float)0.74, (float)0.12, (float)0.8),

    /**
     *
     */
    WINTER((float)63.3, new Range((float)-5.0, (float) 7.0), (float)0.43, (float)0.67, (float)0.8);
    
    private final float averageRain;
    private final Range averageTemp;
    private final float rainProb;
    private final float fogProb;
    private final float averageHumidity;
    
    private Season(float averageRain, 
                   Range averageTemp, 
                   float rainProb, 
                   float fogProb,
                   float averageHumidity) {
        this.averageRain = averageRain;
        this.averageTemp = averageTemp;
        this.rainProb = rainProb;
        this.fogProb = fogProb;
        this.averageHumidity = averageHumidity;
    }

    /**
     *
     * @return
     */
    public float getAverageRain() {
        return averageRain;
    }

    /**
     *
     * @return
     */
    public Range getAverageTemp() {
        return averageTemp;
    }
    
    /**
     *
     * @return
     */
    public float getRainProb() {
        return rainProb;
    }
    
    /**
     *
     * @return
     */
    public float getFogProb() {
        return fogProb;
    }

    /**
     *
     * @return
     */
    public float getAverageHumidity() {
        return averageHumidity;
    }
}