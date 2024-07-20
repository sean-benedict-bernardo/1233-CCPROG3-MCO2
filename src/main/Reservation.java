package main;

import java.util.ArrayList;

import main.rooms.Room;

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
    private NightRates nightRates[];
    private Room room;
    private String discountCode; // this is to be used in getTotalPrice

    /**
     * Reservation constructor
     * 
     * @param guestName    String name of guest
     * @param checkInDate  integer representation of check-in date, 1-30
     * @param checkOutDate integer representation of check-out date, 2-31
     * @param room         Room details
     * @param basePrice    base price as passed from the instantiating Hotel
     */
    public Reservation(String guestName, ArrayList<NightRates> nightRates,
            Room room, float basePrice, String discountCode) {
        this.guestName = guestName;
        this.nightRates = (NightRates[]) nightRates.toArray();
        this.room = room;
        this.discountCode = discountCode;
    }

    public Reservation(String guestName, ArrayList<NightRates> nightRates,
            Room room, float basePrice) {
        this.guestName = guestName;
        this.nightRates = (NightRates[]) nightRates.toArray();
        this.room = room;
        this.discountCode = null;
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

    // no setter because the hotel should not
    // be updating its prices if there are reservations

    public float getNightPrice(int date) {
        int index = date - this.getCheckInDate();
        return (0 <= index && index < nightRates.length)
                ? this.room.getPrice() * nightRates[index].getDayRate()
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
            total += this.room.getPrice() * nightRates[i].getDayRate();
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
                if (this.room.getName().equals("S27"))
                    total *= 0.69f;
                break;

            default:
                break;
        }

        return total;
    }
}
