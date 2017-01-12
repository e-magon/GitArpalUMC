/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clima;

/**
 *
 * @author 5ib10
 */
public class Range {
    private final float min;
    private final float max;

    /**
     *
     * @param min
     * @param max
     */
    public Range(float min, float max) {
        if(min > max) {
            this.min = max;
            this.max = min;
        } else {
            this.min = min;
            this.max = max;
        }
    }

    /**
     *
     * @return
     */
    public float min() {
        return min;
    }

    /**
     *
     * @return
     */
    public float max() {
        return max;
    }
    
    /**
     *
     * @return
     */
    public float range() {
        return max - min;
    }
}
