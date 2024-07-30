package model;

import java.util.ArrayList;

/**
 * This class collates hotels and contains
 * relevant methods that require all hotels to be checeked
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class HotelCollection {
    private ArrayList<Hotel> hotelList;

    /**
     * HotelCollection constructor
     */
    public HotelCollection() {
        this.hotelList = new ArrayList<>();
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
     * Creating new hotel
     * 
     * @param newHotelName  name of new hotel
     * @param firstRoomType character of new room
     * @throws Exception if the hotel name is already taken
     */
    public void addHotel(String newHotelName, char firstRoomType) throws Exception {
        // Empty and invalid newHotelName are thrown here
        Hotel candidateHotel = new Hotel(newHotelName, firstRoomType);

        // Check for uniqueness
        if (!this.isUniqueHotelName(newHotelName, null))
            throw new Exception(newHotelName + " already exists!");
        else {
            this.hotelList.add(candidateHotel);
            System.out.printf("%s | Adding Hotel '%s'\n", "HotelCollection", candidateHotel.getName());
        }
    }

    /**
     * Delete hotel from list
     * 
     * @param key String name of hotel to be deleted
     */
    public void removeHotel(String key) {
        // Find index of room to delete
        for (int i = 0; i < hotelList.size(); i++)
            if (key.equals(this.hotelList.get(i).getName())) {
                System.out.printf("%s | Deleting Hotel '%s'\n", "HotelCollection", key);
                this.hotelList.remove(i);
                break;
            }
    }

    /**
     * Changes hotel name
     * 
     * @param oldHotelName name of hotel to be changed
     * @param newHotelName name of new hotel name
     * @throws Exception thrown if oldHotel is not found,
     *                   newHotelName is invalid or
     *                   an already existing name
     */
    public void updateHotelName(String oldHotelName, String newHotelName) throws Exception {
        Hotel targetHotel = this.getHotel(oldHotelName);

        if (targetHotel == null)
            throw new Exception("Hotel not found");
        else if (!Hotel.isValidHotelName(newHotelName))
            throw new Exception("Invalid hotel name");
        else if (!this.isUniqueHotelName(newHotelName, targetHotel))
            throw new Exception(newHotelName + " already exists!");
        else if (!oldHotelName.equals(newHotelName)) {
            targetHotel.setName(newHotelName);
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
     * Hotel getter by hotel name
     * 
     * @param key
     * @return matched hotel, null if none found
     */
    public Hotel getHotel(String key) {
        for (Hotel hotel : hotelList) {
            if (key.equals(hotel.getName()))
                return hotel;
        }

        return null;
    }

    /**
     * Getter for all hotels
     * 
     * @return ArrayList of Hotels
     */
    public ArrayList<Hotel> getHotels() {
        return this.hotelList;
    }
}
