package model;

public class NightRates {
    private int date; // dunno if i will need this but i'll keep it around
    private float nightRate;

    public NightRates(int date) {
        this.date = date;
        this.nightRate = 1.0f;
    }

    public int getDate() {
        return this.date;
    }

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
