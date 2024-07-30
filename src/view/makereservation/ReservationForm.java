package view.makereservation;

import java.awt.*;
import javax.swing.*;

import model.Hotel;
import model.rooms.Room;
import view.common.MyStyles;
import view.common.components.MyComponents;

/**
 * ReservationForm contains the GUI
 * that allows user to create hotel
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ReservationForm extends JDialog {
    private JPanel containerPanel;

    private JPanel availabilityPanel;
    private JLabel infoAvailabilityDate[] = new JLabel[Hotel.NUM_NIGHTS];

    // input fields
    private JTextField guestNameField;
    private JComboBox<String> guestRoomSelection;
    private JSpinner guestCheckIn;
    private JSpinner guestCheckOut;
    private JTextField guestDiscountCode;
    private JButton submitReservation;
    private JButton cancelReservation;

    /**
     * ReservationForm Constructor
     * 
     * @param hotel Hotel whose will get new reservation
     */
    public ReservationForm(Hotel hotel) {
        super((Dialog) null);

        setLayout(new BorderLayout());
        setBackground(MyStyles.color.BACKGROUND);
        setTitle(hotel.getName() + " Reservation Form");

        this.initFrame(hotel);
        this.updateCalendar(hotel.getRooms().get(0));

        setMinimumSize(MyStyles.misc.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    /**
     * Initializes the Frame
     */
    public void initFrame(Hotel hotel) {
        this.containerPanel = new JPanel(new GridBagLayout());
        this.containerPanel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        containerPanel.setBackground(MyStyles.color.BACKGROUND);
        containerPanel.setForeground(MyStyles.color.FOREGROUND);

        this.guestNameField = new JTextField("", 20);
        this.guestRoomSelection = new JComboBox<String>(hotel.getRoomNames());
        this.guestRoomSelection.setSelectedIndex(0);
        this.guestCheckIn = new JSpinner(new SpinnerNumberModel(1, 1, Hotel.NUM_NIGHTS - 1, 1));
        this.guestCheckOut = new JSpinner(new SpinnerNumberModel(2, 2, Hotel.NUM_NIGHTS, 1));
        this.guestDiscountCode = new JTextField("", 20);
        this.submitReservation = MyComponents.button("Make Reservation");
        this.cancelReservation = MyComponents.button("Cancel Reservation");

        this.initAvailabilityCalendar(hotel.getRooms().get(0));

        // NORTH
        add(MyComponents.titleText("Booking under " + hotel.getName()), BorderLayout.NORTH);

        // Adding Components

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.containerPanel.add(MyComponents.bodyText("Guest Name: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestNameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Select Room: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestRoomSelection, gbc);

        // Room Availability
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.containerPanel.add(this.availabilityPanel, gbc);
        gbc.gridy++;
        this.containerPanel.add(MyComponents.bodyText("✓ - Room is available | ✗ - Room is Reserved"), gbc);
        gbc.anchor = GridBagConstraints.WEST;

        // Checkinout dates
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Check-in Date: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestCheckIn, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Check-out Date: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestCheckOut, gbc);

        // Discount code
        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(MyComponents.bodyText("Discount Code: "), gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.guestDiscountCode, gbc);

        // Buttons
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy++;
        gbc.gridx = 0;
        this.containerPanel.add(this.submitReservation, gbc);
        gbc.gridx = 1;
        this.containerPanel.add(this.cancelReservation, gbc);

        add(this.containerPanel, BorderLayout.CENTER);
    }

    /**
     * Initializes the calendar of availability
     * 
     * @param room info whose dates are reference
     */
    private void initAvailabilityCalendar(Room room) {
        this.availabilityPanel = new JPanel(new GridBagLayout());
        this.availabilityPanel.setBackground(MyStyles.color.BACKGROUND);
        GridBagConstraints calendarConstraints = new GridBagConstraints();

        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i] = MyComponents.bodyText("", JLabel.RIGHT);

        calendarConstraints.ipadx = 5;
        calendarConstraints.ipady = 2;
        calendarConstraints.weightx = 1;
        calendarConstraints.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < infoAvailabilityDate.length; i++) {
            calendarConstraints.gridx = i % 7;
            calendarConstraints.gridy = i / 7;

            availabilityPanel.add(infoAvailabilityDate[i], calendarConstraints);
        }
    }

    /**
     * 
     * @return String value of input text box
     */
    public String getGuestNameField() {
        String textValue = this.guestNameField.getText();
        return (!textValue.equals("")) ? textValue : null;
    }

    /**
     * Getter for room selection
     * 
     * @return selected room name from dropdown
     */
    public String getRoomSelection() {
        return this.guestRoomSelection.getSelectedItem().toString();
    }

    /**
     * Getter for checkInDate
     * 
     * @return checkInDate from JSpinner
     */
    public int getCheckInDate() {
        return Integer.parseInt(this.guestCheckIn.getValue().toString());
    }

    /**
     * Getter for CheckOutDate
     * 
     * @return CheckOutDate from JSpinner
     */
    public int getCheckOutDate() {
        return Integer.parseInt(this.guestCheckOut.getValue().toString());
    }

    /**
     * Getter for discountCode
     * 
     * @return discountCode from JTextInput
     */
    public String getDiscountCode() {
        return this.guestDiscountCode.getText();

    }

    // object getters

    /**
     * Getter for create reservation button
     * 
     * @return JButton
     */
    public JButton getSubmitButton() {
        return this.submitReservation;
    }

    /**
     * Getter for cancel reservation button
     * 
     * @return JButton
     */
    public JButton getCancelButton() {
        return this.cancelReservation;
    }

    /**
     * Getter the entire JComboBox
     * 
     * @return JComboBox of room selection
     */
    public JComboBox<String> getRoomSelectionObject() {
        return this.guestRoomSelection;
    }

    /**
     * Updates calendar of availability
     * 
     * @param room whose availability is shown
     */
    public void updateCalendar(Room room) {
        for (int i = 0; i < infoAvailabilityDate.length; i++)
            infoAvailabilityDate[i]
                    .setText(String.format("%d - %c", i + 1, !room.getDayAvailability(i) ? '✓' : '✗'));
    }
}
