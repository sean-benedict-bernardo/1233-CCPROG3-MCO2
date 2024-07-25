package driver;

import javax.swing.JButton;

import model.Hotel;
import model.HotelCollection;
import model.Reservation;
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
            this.manageHotelPanel.getSaveHotelName().addActionListener(e -> this.updateHotelName());
            this.manageHotelPanel.getSaveBasePrice().addActionListener(e -> this.updateBasePrice());
            this.manageHotelPanel.getSaveDateModifier().addActionListener(e -> this.updateDatePriceModifier());
            this.manageHotelPanel.getDeleteHotel().addActionListener(e -> this.deleteHotel());
        }

        this.manageRoomPanel = (ManageRooms) this.gui.getCardComponent(1);
        if (manageRoomPanel instanceof ManageRooms) {
            this.manageRoomPanel.getRoomAdd().addActionListener(e -> this.addRoom(this.manageRoomPanel.getRoomType()));
            this.manageRoomPanel.getRoomDelete()
                    .addActionListener(e -> this.deleteRoom(this.manageRoomPanel.getSelectedRoom()));
        }

        if (this.hotel.getNumReservations() > 0) {
            this.manageReservationsPanel = (ManageReservations) this.gui.getCardComponent(2);
            if (this.manageReservationsPanel instanceof ManageReservations) {
                this.manageReservationsPanel.getDropDown().addActionListener((e) -> {
                    this.updateReservationInfo(this.manageReservationsPanel.getSelectedReservation());
                });
                this.manageReservationsPanel.getDeleteReservation().addActionListener(e -> this.deleteReservation());
            }
        }
    }

    private void updateHotelName() {
        try {
            ManageHotelPanel manageHotelPanel = (ManageHotelPanel) this.gui.getCardComponent(0);
            String hotelName = manageHotelPanel.getHotelName();

            // TODO: check if this logic should be moved to HotelCollection
            // Check if hotel name field is empty
            if (hotelName == null || hotelName.isEmpty())
                throw new Exception("Invalid Input!");
            // Check if hotel name is unique
            else if (!this.hotelList.isUniqueHotelName(hotelName, hotel))
                throw new Exception(hotelName + " already exists!");
            // Changes if hotel is different from the original
            // Otherwise nothing should happen
            else if (!this.hotel.getName().equals(hotelName)) {
                Alert.displayAlert("Changing name of " + this.hotel.getName() + " to " + hotelName);
                this.hotel.setName(hotelName);
            }
            // If the new name is the same as the old, nothing happens
        } catch (Exception omg) {
            Alert.displayAlert(omg);
        }
    }

    private void updateBasePrice() {
        try {
            ManageHotelPanel manageHotelPanel = (ManageHotelPanel) this.gui.getCardComponent(0);
            float basePrice = manageHotelPanel.getBasePrice();

            // Exceptions are done within setBasePrice
            this.hotel.setBasePrice(basePrice);

        } catch (Exception omg) {
            Alert.displayAlert(omg);
        }
    }

    private void updateDatePriceModifier() {
        try {
            ManageHotelPanel manageHotelPanel = (ManageHotelPanel) this.gui.getCardComponent(0);
            float[] dateModifier = manageHotelPanel.getDateModifier();

            for (int i = 0; i < this.hotel.getNightRates().length; i++)
                this.hotel.setNightRate(i, dateModifier[i]);
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }

    private void deleteHotel() {
        try {
            boolean hasConfirmed = UserInput.confirmAction("Do you want to delete this hotel");

            if (hasConfirmed) {
                this.hotelList.removeHotel(this.hotel.getName());
                Alert.displayAlert("Deleting " + this.hotel.getName());
                System.out.println("Deleted: " + this.hotel.getName());
                this.hideWindow();
            }
        } catch (Exception err) {
            Alert.displayAlert(err);
        }
    }

    private void addRoom(char roomType) {
        try {
            this.hotel.addRoom(roomType);
            this.manageRoomPanel.updateContent();
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }

    private void deleteRoom(String selectedRoom) {
        try {
            this.hotel.removeRoom(selectedRoom);
            this.manageRoomPanel.updateContent();
        } catch (Exception e) {
            Alert.displayAlert(e);
        }
    }

    private void updateReservationInfo(String id) {
        this.manageReservationsPanel.updateReservation(id);
    }

    private void deleteReservation() {
        try {
            String deleteId = this.manageReservationsPanel.getSelectedReservation();
            this.hotel.removeReservation(deleteId);

            boolean isEmpty = this.hotel.getNumReservations() == 0;
            // Exit manage Reservation menu
            if (isEmpty) {
                this.gui.updateReservationButton(isEmpty);
                this.gui.showCard(0);
            } else
                this.manageReservationsPanel.updateReservationList(this.hotel.getReservationIds());

        } catch (Exception omg) {
            Alert.displayAlert(omg);
        }
    }

    public void hideWindow() {
        System.out.println("ManageHotel: window hidden");
        this.gui.setVisible(false);
        this.gui.dispose();
    }
}
