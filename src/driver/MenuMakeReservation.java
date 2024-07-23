package driver;

import model.Hotel;
import view.common.auxiliary.Alert;
import view.makereservation.ReservationForm;

/**
 * MenuMakeReservation is the Controller class
 * for the "Make Reservation" functionalities.
 * 
 * Allows user to book a hotel room by entering the
 * Guest name, room, check-in and check-out dates,
 * and discount codes if applicable
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MenuMakeReservation {
    private ReservationForm form;
    private Hotel hotel;

    /**
     * MenuMakeReservation Constructor
     * 
     * @param hotel hotel to add Reservation to
     */
    public MenuMakeReservation(Hotel hotel) {
        this.hotel = hotel;
        this.form = new ReservationForm(this.hotel);

        this.initButtons();
        form.setModal(true);
        form.setVisible(true);
    }

    /**
     * Initializing form buttons
     */
    public void initButtons() {
        this.form.getRoomSelectionObject().addActionListener((e) -> {
            this.form.updateCalendar(this.hotel.getRoom(this.form.getRoomSelection()));
        });

        this.form.getSubmitButton().addActionListener((e) -> {
            System.out.println("MenuMakeReservation: attempting to create reservation");
            this.makeReservation();
        });

        this.form.getCancelButton().addActionListener((e) -> {
            System.out.println("MenuMakeReservation: exiting to main menu");
            this.form.dispose();
        });
    }

    /**
     * Method to handle the getting and creation of Reservations
     */
    public void makeReservation() {
        String guestName = form.getGuestNameField();
        String roomName = form.getRoomSelection();
        int checkInDate = form.getCheckInDate();
        int checkOutDate = form.getCheckOutDate();
        String discountCode = form.getDiscountCode();

        try {
            if (guestName == null) {
                throw new Exception("No name entered!");
            } else {
                if (discountCode != null)
                    this.hotel.createReservation(guestName, checkInDate, checkOutDate, roomName, discountCode);
                else
                    this.hotel.createReservation(guestName, checkInDate, checkOutDate, roomName);

                Alert.displayAlert("Creating reservation for " + guestName);
                // return to main menu once successful
                this.form.dispose();
            }
        } catch (Exception err) {
            Alert.displayAlert(err);
        }
    }
}