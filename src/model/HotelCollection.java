package model;

import java.util.ArrayList;

public class HotelCollection {
    private ArrayList<Hotel> hotelList;

    public HotelCollection() {
        this.hotelList = new ArrayList<>();
    }

    /**
     * Adds hotel to hotelList
     * 
     * @param newHotel hotel to be added
     */
    public void addHotel(Hotel newHotel) {
        this.hotelList.add(newHotel);
    }

    /**
     * Delete hotel from list
     * 
     * @param key String name of hotel to be deleted
     */
    public void removeHotel(String key) {
        int deleteIndex = this.getHotelIndex(key);
        if (deleteIndex > -1)
            this.hotelList.remove(deleteIndex);
    }

    /**
     * Checks if potential hotel name is unique
     * 
     * @param key         hotel name to check from hotelList
     * @param hotelIgnore hotel to be skipped
     * @return boolean whether hotel name is unique or not
     */
    public boolean isUniqueHotelName(String key, Hotel hotelIgnore) {
        // Return true if
        // - no hotel of matching name is found OR
        // - the supplied hotel is equivalent to the hotelIgnore
        try {
            return this.getHotel(key) == null || hotelIgnore.getName().equals(this.getHotel(key).getName());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Getter for number of hotels in system
     * 
     * @return integer number of hotels in system
     */
    public int getNumHotels() {
        return this.hotelList.size();
    }

    /**
     * Hotel Index Getter by hotel name
     * 
     * @param key hotel name to be surched
     * @return integer index of hotel within hotelList, -1 if not found
     */
    private int getHotelIndex(String key) {
        for (int i = 0; i < hotelList.size(); i++) {
            if (key.equals(this.hotelList.get(i).getName()))
                return i;
        }
        return -1;
    }

    /**
     * Hotel getter by hotel name
     * 
     * @param key
     * @return Hotel
     */
    public Hotel getHotel(String key) {
        for (Hotel hotel : hotelList) {
            if (key.equals(hotel.getName()))
                return hotel;
        }

        return null;
    }

    public ArrayList<Hotel> getHotels() {
        return this.hotelList;
    }
}
