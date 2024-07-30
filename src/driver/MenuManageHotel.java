package driver;

import javax.swing.JButton;

import model.Hotel;
import model.HotelCollection;
import model.rooms.Room;
import view.common.auxiliary.Alert;
import view.common.auxiliary.UserInput;
import view.managehotel.ManageHotel;
import view.managehotel.ManageHotelPanel;
import view.managehotel.ManageReservations;
import view.managehotel.ManageRooms;

/**
 * MenuManageHotel is the Controller class
 * for the "Manage Hotel" functionalities.
 * 
 * Specific functionalities are explained in the nested class
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class MenuManageHotel {
    private ManageHotel gui;
    private Hotel hotel;
    private HotelCollection hotelList;

    private ManageHotelPanel manageHotelPanel;
    private ManageRooms manageRoomPanel;
    private ManageReservations manageReservationsPanel;

    /**
     * MenuManageHotel constructor
     * 
     * @param hotel
     * @param hotelList for reference
     */
    public MenuManageHotel(Hotel hotel, HotelCollection hotelList) {
        this.hotel = hotel;
        this.hotelList = hotelList;
        this.gui = new ManageHotel(this.hotel);
        this.initToolBar();
        this.initButtons();
        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    /**
     * Initializes JToolBar
     */
    private void initToolBar() {
        JButton buttons[] = this.gui.getButtons();

        for (int i = 0; i < buttons.length - 1; i++) {
            final int leIndexJames = i;
            buttons[leIndexJames].addActionListener(e -> this.gui.showCard(leIndexJames));
        }
        buttons[3].addActionListener(e -> this.hideWindow());

    }

    /**
     * Initializes buttons
     */
    private void initButtons() {
        this.manageHotelPanel = (ManageHotelPanel) this.gui.getCardComponent(0);
        if (manageHotelPanel instanceof ManageHotelPanel) {
            ManageHotelMethods hotelMethods = new ManageHotelMethods();
            manageHotelPanel.getSaveHotelName().addActionListener(e -> hotelMethods.updateHotelName());
            manageHotelPanel.getSaveBasePrice().addActionListener(e -> hotelMethods.updateBasePrice());
            manageHotelPanel.getSaveDateModifier().addActionListener(e -> hotelMethods.updateDatePriceModifier());
            manageHotelPanel.getDeleteHotel().addActionListener(e -> hotelMethods.deleteHotel());
        }

        this.manageRoomPanel = (ManageRooms) this.gui.getCardComponent(1);
        if (manageRoomPanel instanceof ManageRooms) {
            ManageRoomsMethods roomMethods = new ManageRoomsMethods();
            manageRoomPanel.getRoomAdd()
                    .addActionListener(e -> roomMethods.addRoom(manageRoomPanel.getRoomType()));
            manageRoomPanel.getRoomDelete()
                    .addActionListener(e -> roomMethods.deleteRoom(manageRoomPanel.getSelectedRoom()));
        }

        // Do not instantiate if there are no reservations
        if (this.hotel.getNumReservations() > 0) {
            this.manageReservationsPanel = (ManageReservations) this.gui.getCardComponent(2);
            if (manageReservationsPanel instanceof ManageReservations) {
                ManageReservationsMethods reservationsMethods = new ManageReservationsMethods();
                manageReservationsPanel.getDropDown().addActionListener((e) -> {
                    reservationsMethods.updateReservationInfo(manageReservationsPanel.getSelectedReservation());
                });
                manageReservationsPanel.getDeleteReservation()
                        .addActionListener(e -> reservationsMethods.deleteReservation());
            }
        }
    }

    /**
     * ManageHotelMethods is the nested class
     * for the "Manage Hotel" functionalities pertaining
     * to modifying the following the hotel level info
     * - Hotel Name
     * - Base Price
     * - Date Price Modifer
     * 
     * @author Sean Benedict Bernardo
     * @author Luis Andrew Madridijo
     */
    public class ManageHotelMethods {
        private ManageHotelPanel manageHotelPanel;

        /**
         * ManageHotelMethods Constructor
         */
        public ManageHotelMethods() {
            this.manageHotelPanel = (ManageHotelPanel) gui.getCardComponent(0);
        };

        /**
         * Updates hotel's name
         */
        private void updateHotelName() {
            try {
                String oldHotelName = hotel.getName();
                String newHotelName = manageHotelPanel.getHotelName();

                hotelList.updateHotelName(hotel.getName(), newHotelName);

                if (!oldHotelName.equals(newHotelName))
                    Alert.displayAlert("Changing name of " + oldHotelName + " to " + newHotelName);

            } catch (Exception omg) {
                Alert.displayAlert(omg);
            }
        }

        /**
         * Updates hotel's base price
         */
        private void updateBasePrice() {
            try {
                float basePrice = manageHotelPanel.getBasePrice();

                // Exceptions are done within setBasePrice
                hotel.setBasePrice(basePrice);
            } catch (Exception omg) {
                Alert.displayAlert(omg);
            }
        }

        /**
         * Updates Date Price Modifier of all dates
         */
        private void updateDatePriceModifier() {
            try {
                float[] dateModifier = manageHotelPanel.getDateModifier();

                for (int i = 0; i < hotel.getNightRates().length; i++)
                    hotel.setNightRate(i, dateModifier[i]);
            } catch (Exception e) {
                Alert.displayAlert(e);
            }
        }

        /**
         * 
         */
        private void deleteHotel() {
            try {
                boolean hasConfirmed = UserInput.confirmAction("Do you want to delete this hotel");

                if (hasConfirmed) {
                    hotelList.removeHotel(hotel.getName());
                    Alert.displayAlert("Deleting " + hotel.getName());
                    System.out.println("Deleted: " + hotel.getName());
                    hideWindow();
                }
            } catch (Exception err) {
                Alert.displayAlert(err);
            }
        }
    }

    /**
     * ManageRoomsMethods is the nested class
     * for the "Manage Hotel" functionalities
     * pertaining to adding and removing rooms
     * 
     * @author Sean Benedict Bernardo
     * @author Luis Andrew Madridijo
     */
    public class ManageRoomsMethods {
        private ManageRooms manageRoomPanel;

        /**
         * ManageRoomsMethods constructor
         */
        public ManageRoomsMethods() {
            this.manageRoomPanel = (ManageRooms) gui.getCardComponent(1);
        }

        /**
         * Adds room to hotel
         * 
         * @param roomType character of room type
         */
        private void addRoom(char roomType) {
            try {
                Room localRoom = hotel.addRoom(roomType);
                // if exception hasnt been thrown, inform user and update panel
                Alert.displayAlert("Room " + localRoom.getName() + " has been added.");
                manageRoomPanel.updateContent();
            } catch (Exception e) {
                Alert.displayAlert(e);
            }
        }

        /**
         * Deletes room from hotel
         * 
         * @param selectedRoom String ID of room to be deleted
         */
        private void deleteRoom(String selectedRoom) {
            try {
                hotel.removeRoom(selectedRoom);
                // if exception hasnt been thrown, inform user and update panel
                Alert.displayAlert("Room " + selectedRoom + " has been removed.");
                manageRoomPanel.updateContent();
            } catch (Exception e) {
                Alert.displayAlert(e);
            }
        }
    }

    /**
     * ManageReservationsMethods is the nested class
     * for the "Manage Hotel" functionalities
     * pertaining removing reservation
     * 
     * @author Sean Benedict Bernardo
     * @author Luis Andrew Madridijo
     */
    public class ManageReservationsMethods {
        private ManageReservations manageReservationsPanel;

        /**
         * ManageReservationsMethods constructor
         */
        public ManageReservationsMethods() {
            this.manageReservationsPanel = (ManageReservations) gui.getCardComponent(2);
        }

        /**
         * Updates the JPanel when user switches
         * reservation from the JComboBox
         * 
         * @param id
         */
        private void updateReservationInfo(String id) {
            manageReservationsPanel.updateReservation(id);
        }

        /**
         * Handles the deletion of reservation
         * also updates whether the tab is still viewable
         */
        private void deleteReservation() {
            try {
                String deleteId = manageReservationsPanel.getSelectedReservation();
                hotel.removeReservation(deleteId);

                boolean isEmpty = hotel.getNumReservations() == 0;
                // Exit manage Reservation menu if there are no more reservations
                if (isEmpty) {
                    Alert.displayAlert("No more rooms\nexiting tab.");
                    gui.updateReservationButton(isEmpty);
                    manageHotelPanel.updateValidity(isEmpty);
                    gui.showCard(0);
                } else
                    manageReservationsPanel.updateReservationList(hotel.getReservationIds());

            } catch (Exception omg) {
                Alert.displayAlert(omg);
            }
        }
    }

    /**
     * Hides gui window
     */
    public void hideWindow() {
        System.out.println("ManageHotel: window hidden");
        this.gui.setVisible(false);
        this.gui.dispose();
    }
}
