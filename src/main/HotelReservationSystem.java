package main;

import java.util.ArrayList;

import main.rooms.Room;
import viewer.Alert;
import viewer.Auxiliary;

/**
 * This class simulates a hotel reservation system
 * that can add a hotel to the system, display
 * information about a hotel, modify information
 * about a hotel, and create a hotel reservation.
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */

public class HotelReservationSystem {
    ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    /**
     * checks if potential hotel name is unique
     * 
     * @param key         hotel name to check from hotelList
     * @param ignoreIndex index in list to be ignored
     * @return boolean whether hotel name is unique or not
     */
    private boolean isUniqueHotelName(String key, int ignoreIndex) {
        int i = 0;
        for (Hotel hotel : this.hotelList) {
            if ((key.equals(hotel.getName())) && i != ignoreIndex)
                // We assume no one will name their hotel /EXIT
                return false;
            i++;
        }

        return true;
    }

    /**
     * Adds a new hotel to the system with a unique name provided by the user
     * This includes a check on whether the user entered a unique hotel name
     */
    public void createHotel() {
        String hotelName = Auxiliary.getString("Enter name of hotel or '/EXIT' to cancel");
        while (!isUniqueHotelName(hotelName, -1)) {

            hotelName = Auxiliary.getString(">>> ", true);
        }
    }

    /**
     * Allows user to modify details of a hotel, such as
     * 1. Changing the hotel name
     * 2. Adding room
     * 3. Remove a specified room
     * 4. Updating the room price across the entire hotel
     * 5. Delete reservation
     * 6. Delete hotel
     * 
     * @param hotelIndex index of the hotel in the system to be modified
     */
    public void manageHotel(int hotelIndex) {
        int userInt = -1;
        boolean confirmDeletion = false;
        boolean confirmChange = false;
        Hotel hotelLocal = this.hotelList.get(hotelIndex);

        do {
            // Print Menu options for Hotel
            confirmDeletion = confirmChange = false;
            System.out.println();
            System.out.printf("=== MANAGING %s ===\n", hotelLocal.getName());
            System.out.println("   [1] Change Hotel Name");
            System.out.println("   [2] Add Room");
            System.out.println("   [3] Remove Room");
            System.out.println("   [4] Update Room Price");
            System.out.println("   [5] Delete Reservation");
            System.out.println("   [6] Delete Hotel");
            System.out.println("   [7] Return to main menu");
            System.out.println();

            userInt = Auxiliary.getInt("Enter Choice", 1, 7);

            switch (userInt) {
                case 1:
                    String hotelName = Auxiliary.getString("Enter new name of hotel");

                    while (!isUniqueHotelName(hotelName, hotelIndex)) {
                        System.out.println("Name is already taken!");
                        hotelName = Auxiliary.getString(">>> ", true);
                    }

                    confirmChange = Auxiliary.getBoolean("Do you confirm renaming to " + hotelName + "?");
                    if (confirmChange)
                        if (isUniqueHotelName(hotelName, hotelIndex)) // Safety net
                            hotelLocal.setName(hotelName);

                    break;

                case 2:
                    int roomType = Auxiliary.getInt("Enter room type:", 1, 3) - 1;
                    try {
                        hotelLocal.addRoom(roomType);
                    } catch (Exception e) {
                        Alert.displayAlert(e);
                    }
                    break;

                case 3:
                    hotelLocal.printRooms();
                    System.out.println();
                    String roomName = Auxiliary.getString("Enter room to delete");
                    hotelLocal.removeRoom(roomName);
                    break;

                case 4:
                    if (hotelLocal.getNumReservations() > 0) {
                        System.out.println("There are still active reservations!");
                    } else {
                        System.out.println("Current Base Price: " + hotelLocal.getBasePrice());
                        float newRoomPrice = Auxiliary.getFloat("Enter new base price", 100.0f);
                        confirmChange = Auxiliary.getBoolean("Do you want to keep your changes?");
                        if (confirmChange)
                            try {
                                hotelLocal.setBasePrice(newRoomPrice);
                            } catch (Exception e) {
                                Alert.displayAlert(e);
                            }
                    }
                    break;

                case 5:
                    if (hotelLocal.getNumReservations() == 0)
                        System.out.println("There are no reservations!");
                    else {
                        int anotherUserInt = Auxiliary.getInt("Enter # of reservation to delete", 1,
                                hotelLocal.getNumReservations()) - 1;
                        confirmChange = Auxiliary.getBoolean("Do you want to keep your changes?");
                        if (confirmChange)
                            try {
                                hotelLocal.removeReservation(anotherUserInt);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                    }
                    break;

                case 6:
                    confirmDeletion = Auxiliary.getBoolean("Would you like to delete " + hotelLocal.getName());

                    if (confirmDeletion) {
                        System.out.println("Deleting " + hotelLocal.getName() + "...");

                        this.hotelList.remove(hotelIndex);

                        // Loop breaker
                        userInt = 7;
                    }
                    break;
                case 7:
                    System.out.println("Returning to main menu");
                    break;
            }
        } while (userInt != 7);
    }

    /**
     * Allows user to view certain information of the hotel such as
     * 1. General hotel information
     * 2. Room availability on a certain day
     * 3. Information about a selected room
     * 4. View details of a select Reservation
     * 
     * @param hotelIndex index of the hotel in the system whose information is to be
     *                   displayed
     */
    public void viewHotel(int hotelIndex) {
        Hotel localHotel = this.hotelList.get(hotelIndex);
        int userInt = -1;

        do {
            System.out.println();
            System.out.printf("=== VIEWING HOTEL %s ===\n", localHotel.getName());
            System.out.println("    [1] Hotel Information");
            System.out.println("    [2] View Room Availability");
            System.out.println("    [3] View Room Information");
            System.out.println("    [4] View Reservations");
            System.out.println("    [5] Return to Main Menu");
            System.out.println();

            userInt = Auxiliary.getInt("Enter Choice", 1, 5);

            switch (userInt) {
                case 1:
                    System.out.println();
                    Auxiliary.printBar(40);
                    System.out.println("  Name: " + localHotel.getName());
                    System.out.println("  Number of Rooms: " + localHotel.getNumRooms());
                    System.out.println("  Number of Reservations: " + localHotel.getNumReservations());
                    System.out.printf("  Projected Earnings: %.2f\n", localHotel.getTotalRevenue());
                    Auxiliary.printBar(40);
                    System.out.println();
                    break;

                case 2:
                    int userDate = Auxiliary.getInt("[1-31] Enter Date to inspect availability", 1, 31);

                    if (1 <= userDate && userDate <= 31)
                        localHotel.printRooms(userDate);
                    break;

                case 3:
                    localHotel.printRooms();
                    String userRoom = Auxiliary.getString("Enter Room Name to inspect availability");
                    Room localRoom = localHotel.getRoom(userRoom);

                    if (localRoom == null) {
                        System.out.println("Room not found!");
                    } else {
                        System.out.println();
                        Auxiliary.printBar(64);
                        System.out.println("Room " + localRoom.getName());
                        System.out.println("Price per Night: " + localRoom.getPrice());
                        Auxiliary.printBar(64);

                        for (int i = 0; i < 31; i++) {
                            System.out.printf("| %2d - %c ", i + 1, localRoom.getDayAvailability(i) ? '/' : 'O');
                            if ((i + 1) % 7 == 0 || i == 30)
                                System.out.println("|");
                        }
                        Auxiliary.printBar(64);
                        System.out.println("Legend");
                        System.out.println("   O - No Booked Reservations");
                        System.out.println("   / - Has Reservations");
                        Auxiliary.printBar(64);
                    }

                    break;

                case 4:
                    if (localHotel.getNumReservations() <= 0) {
                        System.out.println("There's no active reservations!");
                    } else {
                        int userReservation = Auxiliary.getInt("Enter # of reservation", 1,
                                localHotel.getNumReservations());

                        // Safety net
                        if (1 <= userReservation && userReservation <= localHotel.getNumReservations()) {
                            Reservation localReservation = localHotel.getReservation(userReservation - 1);
                            System.out.println();
                            System.out.println("=== Reservation Information ===");
                            System.out.println("  Name: " + localReservation.getGuestName());
                            System.out.println("  Room: " + localReservation.getRoom().getName());
                            System.out.println("  Check-in Date: " + localReservation.getCheckInDate());
                            System.out.println("  Check-out Date: " + localReservation.getCheckOutDate());
                            System.out.println("======== Cost Breakdown =======");
                            System.out.println("  Number of Nights: "
                                    + (localReservation.getCheckOutDate() - localReservation.getCheckInDate()));
                            System.out.println("Nightly Breakdown");
                            for (int i = localReservation.getCheckInDate(); i < localReservation
                                    .getCheckOutDate(); i++) {
                                System.out.printf("  Rate:  %.2f/night\n", localReservation.getNightPrice(i));

                            }
                            System.out.printf("  Total: %.2f/night\n", localReservation.getTotalPrice());
                            Auxiliary.printBar(31);
                            System.out.println();
                        }
                    }
                    break;

                default:
                    break;
            }
        } while (userInt != 5);

    }

    /**
     * Creates a reservation in a praticular hotel with the specifications provided
     * by the user
     * 
     * @param hotelIndex index of the hotel where the reservation will be made
     */
    public void createReservation(int hotelIndex) {
        Hotel localHotel = this.hotelList.get(hotelIndex);

        String guestName = Auxiliary.getString("Enter name of guest");
        int checkInDate = Auxiliary.getInt("[1-30] Enter date of check-in", 1, 30);
        int checkOutDate = Auxiliary.getInt("[2-31] Enter date of check-out", 2, 31);

        localHotel.printRooms(checkInDate, checkOutDate - checkInDate - 1);

        String roomName = Auxiliary.getString("Enter Room Number to book");

        // Validity is done within the method
        localHotel.createReservation(guestName, checkInDate, checkOutDate, roomName);
    }

    /**
     * Prints names of all hotels in the system
     * This assumes there are hotels in hotelList
     */
    public void printHotelList() {
        int i = 0;
        int longestStrLen = 0;

        for (Hotel hotel : hotelList)
            if (hotel.getName().length() > longestStrLen)
                longestStrLen = hotel.getName().length();

        longestStrLen += 16;

        System.out.println();
        Auxiliary.printBar(longestStrLen);
        System.out.println("    Number | Name");
        for (Hotel hotel : hotelList)
            System.out.printf("    [%4d] | %s \n", ++i, hotel.getName());
        Auxiliary.printBar(longestStrLen);
        System.out.println();
    }

    /**
     * Main menu of the Hotel Reservation System
     * Allows the user to do the simulate running multiple hotels such as
     * 1. Creating a new hotel
     * 2. Viewing hotel information
     * 3. Managing individual hotels
     * 4. Simulate the booking of a reservation
     */
    public void mainMenu() {
        int userInt = -1;

        do {
            System.out.println();
            System.out.println("=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("    [1] Create Hotel");
            System.out.println("    [2] View Hotel");
            System.out.println("    [3] Manage Hotels");
            System.out.println("    [4] Make a reservation");
            System.out.println("    [5] Exit Program");
            System.out.println();

            userInt = Auxiliary.getInt("Enter Choice", 1, 5);
            switch (userInt) {
                case 1:
                    this.createHotel();
                    break;
                case 2:
                case 3:
                case 4:
                    // Hotel Selector
                    if (this.hotelList.size() > 0) {

                        this.printHotelList();

                        int hotelIndex = Auxiliary.getInt("Enter index of hotel", 1, this.hotelList.size());
                        hotelIndex--; // Zero Indexing

                        if (0 <= hotelIndex || hotelIndex < this.hotelList.size()) {
                            switch (userInt) {
                                case 2:
                                    this.viewHotel(hotelIndex);
                                    break;
                                case 3:
                                    this.manageHotel(hotelIndex);
                                    break;
                                case 4:
                                    this.createReservation(hotelIndex);
                                    break;
                                default:
                                    // This should NOT be reached
                                    System.out.println("Bazinga");
                                    break;
                            }

                        } else {
                            System.out.println("Hotel not found!");
                        }
                    } else {
                        System.out.println("No hotels in system!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Program...");
                    break;
            }
        } while (userInt != 5);

    }
}