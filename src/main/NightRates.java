package main;

public class NightRates {
    private int date; // dunno if i will need this but i'll keep it around
    private float dayRate = 1.0f;

    public NightRates(int date) {
        this.date = date;
    }

    public int getDate() {
        return this.date;
    }

    public float getDayRate() {
        return dayRate;
    }

    /**
     * Updates rate
     * 
     * @param dayRate
     */
    public void setDayRate(float dayRate) {
        if (0.5f <= dayRate && dayRate <= 1.5f)
            this.dayRate = dayRate;
    }
}
