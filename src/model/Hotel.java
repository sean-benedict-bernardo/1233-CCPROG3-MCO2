package model;

import java.util.*;

import model.rooms.*;
import view.common.auxiliary.Alert;

/**
 * This class simulates a hotel that has rooms
 * and can accept reservations of those rooms.
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */

public class Hotel {
    private String name;
    private float basePrice;
    private ArrayList<Room> roomsList;
    private ArrayList<Reservation> reservationsList;
    private NightRates[] nightRates = new NightRates[31];

    private static HashMap<Character, Integer> compareRoomType = new HashMap<>();

    // initialize HashMap
    static {
        compareRoomType.put('S', 0);
        compareRoomType.put('D', 1);
        compareRoomType.put('E', 2);
        compareRoomType.put(null, -1);
    }

    /**
     * Hotel constructor
     * 
     * @param name String name of hotel
     */

    public Hotel(String name, char roomType) throws Exception {
        this.name = name;
        this.basePrice = 1299.0f;
        this.roomsList = new ArrayList<Room>();
        this.reservationsList = new ArrayList<Reservation>();

        for (int i = 0; i < nightRates.length; i++) {
            this.nightRates[i] = new NightRates(i + 1);
        }

        // to satisfy minimum of one room
        try {
            this.addRoom(roomType);
        } catch (Exception e) {
            throw e;
            // Do nothing
        }
    }

    /*
     * This entire section deals with the room systems
     */

    /**
     * Returns integer index of room given the room name
     * 
     * @param roomName
     * @return integer index of room, -1 if not found
     */

    private int getRoomIndex(String roomName) {
        for (int i = 0; i < this.roomsList.size(); i++) {
            if (roomName.equals(this.roomsList.get(i).getName()))
                return i;
        }
        return -1;
    }

    /**
     * Add a room to roomsList
     * naming convention is room type followed by
     * 
     * @param roomType - integer representation of what type of room is to be added
     */

    public void addRoom(char roomType) throws Exception {
        if (this.roomsList.size() >= 50) {
            throw new Exception("Hotel is full");
        } else if (compareRoomType.get(roomType) == -1) {
            // should not be possible as we are going to be using buttons for this
            throw new Exception("Invalid room type");
        } else if (this.roomsList.size() < 50 && compareRoomType.get(roomType) != -1) {
            int roomNum = 1;

            String roomNumStr = roomType + Integer.toString(roomNum);

            // This loop finds the lowest number available, filling deleted rooms
            // if roomsList has no holes, then it will simply fill the index after the last
            while (getRoomIndex(roomNumStr) != -1) {
                roomNum++;
                roomNumStr = roomType + Integer.toString(roomNum);
            }

            Room newRoom = null;

            switch (roomType) {
                case 'S': // Standard
                    newRoom = new Room(roomNumStr, this.basePrice);
                    break;
                case 'D': // Deluxe
                    newRoom = new DeluxeRoom(roomNumStr, this.basePrice);
                    break;
                case 'E': // Executive
                    newRoom = new ExecutiveRoom(roomNumStr, this.basePrice);
                    break;
            }

            /*
             * Insertion Logic:
             * If the list is empty, add room
             * Otherwise, start from end and move rightward until the ff conditions are met:
             * 1. the new room is within the right category grouping S > D > E
             * 2. the new room is in order within said category
             */
            if (this.roomsList.size() == 0)
                this.roomsList.add(newRoom);
            else {
                int i = this.roomsList.size() - 1;
                while (true && i >= 0) {
                    char localRoomType = this.roomsList.get(i).getRoomType().charAt(0);
                    int localRoomNum = Integer.parseInt(this.roomsList.get(i).getName().substring(1));

                    if (compareRoomType.get(roomType) > compareRoomType.get(localRoomType))
                        break;
                    else if (compareRoomType.get(roomType) == compareRoomType.get(localRoomType)
                            && roomNum > localRoomNum)
                        break;
                    i--;
                }
                i++;
                this.roomsList.add(i, newRoom);
            }
            System.out.printf("Adding Room '%s'\n", roomNumStr);
        }

        // rearranges the list with consideration of the new room types
        // Sorts in order of room type then number
        // Standard >

    }

    /**
     * Deletes room from roomsList
     * In removing a room we check for the following
     * 1. Does the room exist?
     * 2. Does the room have any active bookings?
     * 3. Is the room not the only one left in the hotel?
     * If all checks have been satisfied then we delete the room and return true
     * Don't do anything and return false otherwise
     * 
     * @param roomName name of room to be removed
     * @throws Exception when room fails one of the aforementioned conditions
     */

    public void removeRoom(String roomName) throws Exception {
        // Hotel
        int roomIndex = getRoomIndex(roomName);
        Room localRoom = (roomIndex != -1) ? this.roomsList.get(roomIndex) : null;

        if (localRoom == null) {
            throw new Exception("Room not found!");
        } else if (localRoom.getNumReservedNights() > 0) {
            throw new Exception("Room has an active reservation and cannot be deleted!");
        } else if (this.roomsList.size() == 1) {
            throw new Exception("The hotel must have at least one room!");
        } else {
            System.out.println("Deleting room '" + localRoom.getName() + "'");
            this.roomsList.remove(roomIndex);
        }
    }

    /*
     * This entire section deals with creating and deleting reservations
     */

    /**
     * Validates and creates a reservation in this hotel
     * In creating a reservation, we check for the following:
     * 1. Does a room of a given roomName exist in the hotel?
     * 2. Is this room available during all specified days of the stay?
     * If both conditions are satisfied, add the reservation to the reservationsList
     * otherwise, do nothing
     * 
     * @param guestName    name of guest making the reservation
     * @param checkInDate  date when guest will check-in to the hotel
     * @param checkOutDate date when guest will check-out of the hotel
     * @param roomName     name of the room the guest will be reserved
     *                     assumed to be the string of an integer from 1 - 50
     * @throws Exception if either the room is not found or
     *                   room is occupied in the given date range
     */
    public void createReservation(String guestName, int checkInDate, int checkOutDate, String roomName,
            String discountCode) throws Exception {
        int roomIndex = getRoomIndex(roomName);
        Room localRoom = (roomIndex != -1) ? this.roomsList.get(roomIndex) : null;

        if (localRoom == null)
            throw new Exception("Room not found!");
        else if (!localRoom.checkRoomAvailability(checkInDate, checkOutDate))
            throw new Exception("Given dates are either invalid or are already booked!");
        else {
            System.out.println("newReservation: " + guestName);
            try {
                localRoom.addReservedDays(checkInDate, checkOutDate);
                Reservation newReservation = (roomName != null) ? new Reservation(guestName,
                        Arrays.copyOfRange(this.nightRates, checkInDate, checkOutDate), localRoom,
                        this.basePrice)
                        : new Reservation(guestName,
                                Arrays.copyOfRange(this.nightRates, checkInDate, checkOutDate), localRoom,
                                this.basePrice);
                this.reservationsList.add(newReservation);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void createReservation(String guestName, int checkInDate, int checkOutDate, String roomName)
            throws Exception {
        try {
            this.createReservation(guestName, checkInDate, checkOutDate, roomName, null);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Removes the reservation at a particular index of the reservationsList
     * If the provided index is within the range of reservationList, then delete the
     * reservation at that index
     * else print an error message and do nothing
     * 
     * @param reservationIndex index of the reservation to be deleted
     * @throws Exception when reservationIndex is out of range
     */
    public void removeReservation(int reservationIndex) throws Exception {
        int roomIndex = getRoomIndex(this.reservationsList.get(reservationIndex).getRoom().getName());
        if (reservationIndex < 0 || this.reservationsList.size() - 1 < reservationIndex) {
            throw new Exception("Reservation not found!");
        } else {
            Alert.displayAlert("Deleting reservation of " + this.reservationsList.get(reservationIndex).getGuestName());

            this.roomsList.get(roomIndex).removeReservedDays(this.reservationsList.get(reservationIndex));
            this.reservationsList.remove(reservationIndex);
        }
    }

    // Getters and Setters below

    /**
     * Getter for hotel name
     * 
     * @return String of hotel name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for hotel name
     * 
     * @param name String of new hotel name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for hotel base price
     * 
     * @return float of hotel base price
     */
    public float getBasePrice() {
        return basePrice;
    }

    /**
     * Updates base price of hotel
     * Only updates if the following are satisfied:
     * 1. There are no active bookings
     * 2. The new price is at least than 100.0
     * 
     * @param basePrice new updated base price
     */
    public void setBasePrice(float basePrice) throws Exception {
        if (Float.isNaN(basePrice)){
            throw new Exception("Invalid base price!");
        }
        else if (this.reservationsList.size() != 0)
            throw new Exception("There are still active bookings!");
        else if (basePrice < 100.0f)
            throw new Exception("New price is too low!");
        else {
            this.basePrice = basePrice;

            for (Room room : roomsList) {
                try {
                    room.setPrice(basePrice);
                } catch (Exception e) {
                    Alert.displayAlert(e);
                }
            }
        }
    }

    /**
     * 
     * @param date integer representation of date
     * @return NightRate
     */

    public NightRates getNightRate(int index) {
        return (0 <= index && index <= 30) ? this.nightRates[index] : null;
    }

    public NightRates[] getNightRates() {
        return this.nightRates;
    }

    /**
     * Returns the total revenue across the entire hotel
     * The total revenue is the sum of the total price of all reservations
     * 
     * @return float of total revenue
     */

    public float getTotalRevenue() {
        float sum = 0;

        for (Reservation reservation : reservationsList)
            sum += reservation.getTotalPrice();

        return sum;
    }

    /**
     * Getter for number of rooms
     * 
     * @return integer of room count
     */

    public int getNumRooms() {
        return this.roomsList.size();
    }

    /**
     * Getter for room provided its name
     * returns null if room cannot be found
     * 
     * @param roomName String name of room to return
     * @return Room if it is found, null otherwise
     */
    public Room getRoom(String roomName) {
        int roomIndex = this.getRoomIndex(roomName);
        return (roomIndex > -1) ? this.roomsList.get(roomIndex) : null;
    }

    public ArrayList<Room> getRooms() {
        return this.roomsList;
    }

    public String[] getRoomNames() {
        String[] roomNames = new String[this.getNumRooms()];

        for (int i = 0; i < this.getNumRooms(); i++) {
            roomNames[i] = this.roomsList.get(i).getName();
        }

        return roomNames;
    }

    /**
     * Getter of reservation count
     * 
     * @return integer of number of rooms
     */
    public int getNumReservations() {
        return this.reservationsList.size();
    }

    /**
     * Getter of specific reservation given index
     * 
     * @param index 0-indexed integer of selected reservation
     * @return Reservation if in range, null otherwise
     */
    public Reservation getReservation(int index) {
        return (0 <= index && index < this.reservationsList.size()) ? this.reservationsList.get(index) : null;
    }

    /**
     * Getter of specific reservation given index
     * 
     * @param id String identification assigned to reservation
     * @return Reservation if in range, null otherwise
     */
    public Reservation getReservation(String id) {
        for (Reservation reservation : this.reservationsList)
            if (id.equals(reservation.getId()))
                return reservation;
        return null;
    }

    /**
     * Getter for all reservations
     * 
     * @return ArrayList of Reservations
     */
    public ArrayList<Reservation> getReservations() {
        return this.reservationsList;
    }
}
