package viewer.viewhotel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Hotel;
import viewer.common.MyStyles;
import viewer.common.MyComponents;

public class HotelInfo extends JPanel {
    private Hotel hotel;

    public HotelInfo(Hotel hotel) {
        super();
        this.hotel = hotel;

        this.initFrame();

        setLayout(new GridBagLayout());
        setBackground(MyStyles.color.BACKGROUND);
    }

    public void initFrame() {
        JPanel tableContainer = new JPanel(new GridLayout(4, 2, 12, 8));
        tableContainer.setBackground(MyStyles.color.BACKGROUND);

        String data[][] = {
                { "Name", this.hotel.getName() },
                { "Room Count", "" + this.hotel.getNumRooms() },
                { "Reservation Count", "" + this.hotel.getNumReservations() },
                { "Total Revenue", String.format("%.2f", this.hotel.getTotalRevenue()) }
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                tableContainer.add(MyComponents.bodyText(data[i][j], SwingConstants.LEFT));
            }
        }

        add(tableContainer);
    }
}
