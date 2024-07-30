package model;

/**
 * This class contains the logic
 * pertaining to the Date Price Modifer
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class NightRate {
    private int date;
    private float nightRate;

    /**
     * NightRate constructor
     * 
     * @param date integer of date
     */
    public NightRate(int date) {
        this.date = date;
        this.nightRate = 1.0f;
    }

    /**
     * @return int of date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * @return float of night rate
     */
    public float getNightRate() {
        return nightRate;
    }

    /**
     * Updates nightly rate
     * 
     * @preconditon points of exceptions have been handled at the Hotel level
     * @param nightRate
     */
    public void setNightRate(float nightRate) {
        if (0.5f <= nightRate && nightRate <= 1.5f)
            this.nightRate = nightRate;
    }
}
