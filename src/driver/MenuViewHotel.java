package driver;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Hotel;
import view.common.auxiliary.Alert;
import view.viewhotel.*;

/**
 * MenuViewHotel is the controller class for
 * anything related to the "View Hotel" functionality
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MenuViewHotel {
    private ViewHotel gui;
    private Hotel hotel;

    /**
     * MenuViewHotel Constructor
     * 
     * @param hotel
     */
    public MenuViewHotel(Hotel hotel) {
        this.hotel = hotel;
        this.gui = new ViewHotel(this.hotel);

        this.initToolBar();
        this.initButtons();

        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    /**
     * Attaches ActionListeners to ToolBar buttons
     */
    private void initToolBar() {
        JButton buttons[] = this.gui.getButtons();

        for (int i = 0; i < buttons.length - 2; i++) {
            // Java throws a hissy fit when i is not final
            final int innerIndex = i;
            buttons[i].addActionListener(e -> this.gui.showCard(innerIndex));
        }

        // the card can only show if there are reservations
        if (this.hotel.getNumReservations() > 0)
            buttons[3].addActionListener((e) -> this.gui.showCard(3));
        
        buttons[4].addActionListener(e -> this.hideWindow());
    }

    /**
     * Attaches ActionListeners to child components of ViewHotel
     */
    private void initButtons() {

        JPanel roomAvailabilityPanel = this.gui.getCardComponent(1);
        if (roomAvailabilityPanel instanceof RoomAvailability) {
            JButton[] calendarButtons = ((RoomAvailability) roomAvailabilityPanel).getDateSelector();

            for (JButton jButton : calendarButtons) {
                jButton.addActionListener((e) -> {
                    ((RoomAvailability) roomAvailabilityPanel).updateAvailability(this.hotel.getRooms(),
                            Integer.parseInt(e.getActionCommand()));
                });
            }
        }

        // add eventlistener to the jscrollpane
        JPanel roomInfoPanel = this.gui.getCardComponent(2);
        if (roomInfoPanel instanceof RoomInformation) {
            ArrayList<JButton> roomSelectButtons = ((RoomInformation) roomInfoPanel).getRoomSelectButtons();

            for (JButton jButton : roomSelectButtons) {
                jButton.addActionListener((e) -> {
                    ((RoomInformation) roomInfoPanel).dispRoomInfo(this.hotel.getRoom(e.getActionCommand()));
                });
            }
        }

        // Reservation selector
        if (this.hotel.getNumReservations() > 0) {
            JPanel reservationsPanel = this.gui.getCardComponent(3);

            if (reservationsPanel instanceof Reservations) {
                ArrayList<JButton> reservationSelectButtons = ((Reservations) reservationsPanel)
                        .getReservationSelectButtons();

                for (JButton jButton : reservationSelectButtons) {
                    jButton.addActionListener((e) -> {
                        ((Reservations) reservationsPanel)
                                .updateInformation(hotel.getReservation(jButton.getName()));
                    });
                }
            }
        }
    }

    /**
     * method to hide widnow
     */
    private void hideWindow() {
        System.out.println("ViewHotel: window hidden");
        this.gui.setVisible(false);
        this.gui.dispose();
    }
}
