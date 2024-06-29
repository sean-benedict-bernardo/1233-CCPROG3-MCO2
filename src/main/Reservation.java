package main;

/**
 * This class contains the reservation class
 * 
 * simulates a reservation of a guest in a
 * hotel room for a specific duration and a certain
 * price per night.
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */

public class Reservation {
    private String guestName;
    private int checkInDate, checkOutDate;
    private Room room;
    private float price;

    /**
     * Reservation constructor
     * 
     * @param guestName    String name of guest
     * @param checkInDate  integer representation of check-in date, 1-30
     * @param checkOutDate integer representation of check-out date, 2-31
     * @param room         Room details
     * @param price        base price as passed from the instantiating Hotel
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, float price) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.price = price;
    }

    /**
     * Getter for guest name
     * 
     * @return String of guest name
     */

    public String getGuestName() {
        return this.guestName;
    }

    /**
     * Getter of checkInDate
     * 
     * @return integer represenation of check in date
     */

    public int getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Getter of checkOutDate
     * 
     * @return integer represenation of check out date
     */
    public int getCheckOutDate() {
        return this.checkOutDate;
    }

    /**
     * Getter for room details
     * 
     * @return Room of room
     */
    public Room getRoom() {
        return this.room;
    }

    // no setter because the hotel should not
    // be updating its prices if there are reservations

    /**
     * Getter of reservation price per night
     * 
     * @return float of price per night
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Returns the total price of this specific reservation
     * Total price is defined by the base price per night multiplied by
     * the number of nights
     * 
     * @return float of total price
     */

    public float getTotalPrice() {
        // checkOutDate - checkInDate is the number of nights stayed in the hotel
        // this is needed because reservations are priced
        // by the number of nights in a stay
        return this.price * (this.checkOutDate - this.checkInDate);
    }
}
