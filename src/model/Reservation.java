package model;

import model.rooms.Room;

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
        // assign
        this.id = String.format("%s-%02d%02d", this.room.getName(),
                this.getCheckInDate(), this.getCheckOutDate());
    }

    /**
     * Overloaded constructor with the absence of discount code
     * 
     * @param guestName    String name of guest
     * @param checkInDate  integer representation of check-in date, 1-30
     * @param checkOutDate integer representation of check-out date, 2-31
     * @param room         Room details
     */
    public Reservation(String guestName, NightRates[] nightRates,
            Room room, float basePrice) {
        this(guestName, nightRates, room, basePrice, Reservation.NODISCOUNT);
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

    /**
     * Getter for night price
     * 
     * @param index integer of night relative to this.nightRates
     * @return float nightPrice()
     */
    public float getNightPrice(int index) {
        return (0 <= index && index < nightRates.length)
                ? this.room.getPrice() * nightRates[index].getNightRate()
                : 0.0f;
    }

    /**
     * 
     * @return
     */
    public float getRawTotal() {
        float total = 0;

        for (int i = 0; i < nightRates.length; i++)
            total += this.getNightPrice(i);

        return total;
    }

    /**
     * Getter for deduction
     * 
     * @return float amount to be deducted from raw total
     */
    public float getDiscountDeduction() {
        float rawTotal = this.getRawTotal();

        switch (this.discountCode) {
            case "I_WORK_HERE":
                return rawTotal * 0.10f;
            case "PAYDAY":
                if (getCheckInDate() <= 15 && 15 < getCheckOutDate() ||
                        getCheckInDate() <= 30 && 30 < getCheckOutDate())
                    return rawTotal * 0.07f;
                break;
            case "STAY4_GET1":
                if (getCheckOutDate() - getCheckInDate() >= 5)
                    return this.getNightPrice(0);
                break;
        }
        return 0.0f;
    }

    /**
     * Returns the total price of this specific reservation
     * Total price is defined as the raw title - deduction from discount
     * 
     * @return float of total price
     */
    public float getTotalPrice() {
        return this.getRawTotal() - this.getDiscountDeduction();
    }
}
