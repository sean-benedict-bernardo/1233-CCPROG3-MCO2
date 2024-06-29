package main;

/**
 * This class simulates a room in a hotel
 * that a user can reserve for certain dates.
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class Room {
    private String name;
    // this boolean array represents the days in our
    // hypothetical month that are reserved or not
    // true - reserved
    // false - not reserved
    private boolean daysReserved[] = new boolean[31];
    private float price;

    /**
     * Room contstructor
     * 
     * @param name  String name of room
     * @param price float base price of room as passed from instantiating hotel
     */
    public Room(String name, float price) {
        this.name = name;
        this.price = price;

        // while false by default, this is an added safety measure
        for (int i = 0; i < this.daysReserved.length; i++)
            this.daysReserved[i] = false;
    }

    /**
     * Checks and return boolean of Room availability on given a range
     * 
     * @param checkInDate  integer index of day from 1-30 for check in date
     * @param checkOutDate integer index of day from 2-31 for check out date
     * @return boolean status of whether reservation dates are valid and available
     *         (see code for further details)
     */
    public boolean checkRoomAvailability(int checkInDate, int checkOutDate) {
        // 0-indexing
        --checkInDate;
        --checkOutDate;

        // the boolean below checks for the following:
        // 1. if check in and check out dates are in sequence + not on the same day
        // 2. if the checkInDate is before checkOutDate
        // 3. if the checkInDate does not fall on the 31st
        // 4. if the checkOutDate does not fall on the 31st
        // isClear should be true if all of the above are satisfied
        boolean isClear = checkInDate < checkOutDate &&
                (0 <= checkInDate && checkInDate < 30) &&
                (0 < checkOutDate && checkOutDate <= 30);

        // if isClear was already false then we skip this for-loop check completely
        // check all dates within range to determine if all dates within the range is
        for (int i = checkInDate; i < checkOutDate && isClear; i++) {
            if (this.getDayAvailability(i))
                isClear = false;
        }

        return isClear;
    }

    /**
     * Changes the status of this room's availability on certain days
     * to reserved (i.e. true in daysReserved[]) based on check-in and check-out
     * dates
     * 
     * @param checkInDate  integer index of day from 1-30 for check in date
     * @param checkOutDate integer index of day from 2-31 for check out date
     */
    public void addReservedDays(int checkInDate, int checkOutDate) {
        // If booking can be made, flip reservation to
        // true to represent there's a booking on that
        // This if statement is really just an extra safety net because the statement
        // should have been handled in main
        if (this.checkRoomAvailability(checkInDate, checkOutDate)) {
            // to make life easier with 0-indexing
            checkInDate--;
            checkOutDate--;

            // we exclude the checkOutDate index from the reservation date as the
            // checkout date should be able to accommodate a new reservation
            for (int i = checkInDate; i < checkOutDate; i++)
                this.daysReserved[i] = true;
        }
    }

    /**
     * Changes the status of this room's availability on certain days
     * to not reserved (i.e. false in daysReserved[]) based on a given reservation
     * 
     * @param reservation Reservation containing check in and check out dates
     */
    public void removeReservedDays(Reservation reservation) {
        for (int i = reservation.getCheckInDate(); i < reservation.getCheckOutDate() - 1; i++)
            this.daysReserved[i - 1] = false;
    }

    /**
     * @return integer of the number of nights with daysReserved
     */

    public int getNumReservedNights() {
        int numNights = 0;

        for (boolean dayReserved : daysReserved)
            numNights += (dayReserved) ? 1 : 0;

        return numNights;
    }

    /**
     * Returns availability for a specific day provided index
     * 
     * @param index of date
     * @return boolean status of day, false if invalid index is given
     */
    public boolean getDayAvailability(int index) {
        // ternary is safety check for in index access
        return (0 <= index && index <= 30) ? this.daysReserved[index] : false;
    }

    // Getters and setters

    /**
     * Getter for name
     * 
     * @return String of room name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for price
     * 
     * @return float of room price
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Updates price as Hotel is updated
     * Doesn't do anything if price is less than 100.0
     * 
     * @param price Float of new nightly room rate of hotel and room
     */
    public void setPrice(float price) {
        // should already be handled in Hotel but this is an added safety net
        if (price >= 100.0f)
            this.price = price;
    }
}
