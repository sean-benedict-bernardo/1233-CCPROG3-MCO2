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

public class MenuManageHotel {
    private ManageHotel gui;
    private Hotel hotel;
    private HotelCollection hotelList;

    private ManageHotelPanel manageHotelPanel;
    private ManageRooms manageRoomPanel;
    private ManageReservations manageReservationsPanel;

    public MenuManageHotel(Hotel hotel, HotelCollection hotelList) {
        this.hotel = hotel;
        this.hotelList = hotelList;
        this.gui = new ManageHotel(this.hotel);
        this.initToolBar();
        this.initButtons();
        this.gui.setModal(true);
        this.gui.setVisible(true);
    }

    private void initToolBar() {
        JButton buttons[] = this.gui.getButtons();

        buttons[0].addActionListener(e -> this.gui.showCard(0));
        buttons[1].addActionListener(e -> this.gui.showCard(1));
        if (this.hotel.getNumReservations() > 0)
            buttons[2].addActionListener(e -> this.gui.showCard(2));
        buttons[3].addActionListener(e -> this.hideWindow());

    }

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
            manageRoomPanel.getRoomDeleteAll().addActionListener(e -> roomMethods.deleteAllRooms());
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
     * InnerMenuManageHotel
     */
    public class ManageHotelMethods {
        private ManageHotelPanel manageHotelPanel;

        public ManageHotelMethods() {
            this.manageHotelPanel = (ManageHotelPanel) gui.getCardComponent(0);
        };

        private void updateHotelName() {
            try {
                String hotelName = manageHotelPanel.getHotelName();

                // Check if hotel name field is empty
                if (hotelName == null || hotelName.isEmpty())
                    throw new Exception("Invalid Input!");
                // Check if hotel name is unique
                else if (!hotelList.isUniqueHotelName(hotelName, hotel))
                    throw new Exception(hotelName + " already exists!");
                // Changes if hotel is different from the original
                // Otherwise nothing should happen
                else if (!hotel.getName().equals(hotelName)) {
                    Alert.displayAlert("Changing name of " + hotel.getName() + " to " + hotelName);
                    hotel.setName(hotelName);
                }
                // If the new name is the same as the old, nothing happens
            } catch (Exception omg) {
                Alert.displayAlert(omg);
            }
        }

        private void updateBasePrice() {
            try {
                float basePrice = manageHotelPanel.getBasePrice();

                // Exceptions are done within setBasePrice
                hotel.setBasePrice(basePrice);

            } catch (Exception omg) {
                Alert.displayAlert(omg);
            }
        }

        private void updateDatePriceModifier() {
            try {
                float[] dateModifier = manageHotelPanel.getDateModifier();

                for (int i = 0; i < hotel.getNightRates().length; i++)
                    hotel.setNightRate(i, dateModifier[i]);
            } catch (Exception e) {
                Alert.displayAlert(e);
            }
        }

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

    public class ManageRoomsMethods {
        private ManageRooms manageRoomPanel;

        public ManageRoomsMethods() {
            this.manageRoomPanel = (ManageRooms) gui.getCardComponent(1);
        }

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

        private void deleteAllRooms() {
            try {
                boolean confirmDeletion = true;

                if (hotel.getNumReservations() > 0)
                    confirmDeletion = UserInput.confirmAction("Do you want to delete all rooms? Room "
                            + hotel.getRoomNames()[0] + " will be retained to satisfy minimum");
                if (confirmDeletion) {
                    hotel.removeAllRooms();
                    Alert.displayAlert("All but one rooms have been deleted.");
                }
            } catch (Exception e) {
                Alert.displayAlert(e);
            }
            manageRoomPanel.updateContent();
        }
    }

    public class ManageReservationsMethods {
        private ManageReservations manageReservationsPanel;

        public ManageReservationsMethods() {
            this.manageReservationsPanel = (ManageReservations) gui.getCardComponent(2);
        }

        private void updateReservationInfo(String id) {
            manageReservationsPanel.updateReservation(id);
        }

        private void deleteReservation() {
            try {
                String deleteId = manageReservationsPanel.getSelectedReservation();
                hotel.removeReservation(deleteId);

                boolean isEmpty = hotel.getNumReservations() == 0;
                // Exit manage Reservation menu
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

    public void hideWindow() {
        System.out.println("ManageHotel: window hidden");
        this.gui.setVisible(false);
        this.gui.dispose();
    }
}
