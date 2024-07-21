package viewer.makereservation;

import main.Hotel;
import viewer.common.CSS;
import viewer.common.MyComponents;
import viewer.viewhotel.HotelInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationForm extends JDialog {
    public static final int SHOW_AVAILABILITY = 1;

    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel containerPanel;

    private JTextField guestNameField;

    public ReservationForm(Hotel hotel, int mode) {
        super();
        
        this.containerPanel = new JPanel(gbl);

        this.initFrame();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void initFrame() {
        this.guestNameField = new JTextField();

        add(MyComponents.JLabel("Guest Name"));
        add(this.guestNameField);
    }

    public String getGuestNameField() {
        return this.guestNameField.getText();
    }

    public static void main(String[] args) {
        try {
            JFrame test = new JFrame();
            test.add(new ReservationForm(new Hotel("HotelGemal"), ReservationForm.SHOW_AVAILABILITY));
            test.pack();
            test.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
