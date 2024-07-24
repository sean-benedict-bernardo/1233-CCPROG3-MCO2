package model;

import model.rooms.Room;
import view.common.auxiliary.Alert;

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
    public static final String NODISCOUNT = "__NULL__";

    private String guestName;
    private NightRates nightRates[];
    private Room room;
    private String discountCode; // this is to be used in getTotalPrice
    private String id;

    /**
     * Reservation constructor
     * 
     * @param guestName    String name of guest
     * @param checkInDate  integer representation of check-in date, 1-30
     * @param checkOutDate integer representation of check-out date, 2-31
     * @param room         Room details
     * @param basePrice    base price as passed from the instantiating Hotel
     */
    public Reservation(String guestName, NightRates[] nightRates,
            Room room, float basePrice, String discountCode) {
        this.guestName = guestName;
        this.nightRates = nightRates;
        this.room = room;
        this.discountCode = discountCode;
        this.id = String.format("%s-%02d%02d",
                this.room.getName(),
                this.getCheckInDate(), this.getCheckOutDate());
    }

    public Reservation(String guestName, NightRates[] nightRates,
            Room room, float basePrice) {
        this.guestName = guestName;
        this.nightRates = nightRates;
        this.room = room;
        this.discountCode = Reservation.NODISCOUNT;
        this.id = String.format("%s-%02d%02d",
                this.room.getName(),
                this.getCheckInDate(), this.getCheckOutDate());
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
        return this.nightRates[0].getDate();
    }

    /**
     * Getter of checkOutDate
     * 
     * @return integer represenation of check out date
     */
    public int getCheckOutDate() {
        return this.nightRates[this.nightRates.length - 1].getDate() + 1;
    }

    /**
     * Getter for room details
     * 
     * @return Room of room
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Getter for discount code
     * 
     * @return String of discount code
     */
    public String getDiscountCode() {
        return this.discountCode;
    }

    /**
     * Getter for NightRates
     * 
     * @return Array of all NightRates in the reservation
     */
    public NightRates[] getNightRates() {
        return this.nightRates;
    }

    /**
     * Getter for Id
     * 
     * @return String Id
     */
    public String getId() {
        return this.id;
    }

    // no setter because the hotel should not
    // be updating its prices if there are reservations
    public float getNightPrice(int index) {
        return (0 <= index && index < nightRates.length)
                ? this.room.getPrice() * nightRates[index].getNightRate()
                : 0.0f;
    }

    /**
     * Returns the total price of this specific reservation
     * Total price is defined by the base price per night multiplied by
     * the number of nights
     * 
     * @return float of total price
     */

    public float getTotalPrice() {
        // this is needed because reservations are priced
        // by the number of nights in a stay

        float total = 0;
        boolean isPayday = false;

        for (int i = 0; i < nightRates.length; i++) {
            if ("STAY4_GET1".equals(this.discountCode)
                    && this.getCheckOutDate() - this.getCheckInDate() >= 5)
                continue;
            if (!isPayday && (nightRates[i].getDate() == 15 || nightRates[i].getDate() == 30))
                isPayday = true;

            total += this.room.getPrice() * nightRates[i].getNightRate();
        }

        switch (this.discountCode) {
            case "I_WORK_HERE":
                total *= .90f;
                break;
            case "PAYDAY":
                if (isPayday)
                    total *= .93f;
                break;
            case "0P3N_SESSION~":
                if (this.room.getName().equals("S27")) {
                    total *= 0.69f;
                    Alert.displayAlert("Hello there, you found my easter egg >:}");
                }
                break;

            default:
                break;
        }

        return total;
    }
}
