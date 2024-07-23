package view.common.auxiliary;

import javax.swing.JOptionPane;

/**
 * The Alert class is a wrapper of common showMessageDialog calls
 */
public class Alert {
    /**
     * Simply displays an alert
     * 
     * @param message String to be displayed
     */
    public static void displayAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Hotel Reservation System", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Simply displays an alert
     * 
     * @param message String to be displayed
     */
    public static void displayAlert(Exception exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Alert!", JOptionPane.ERROR_MESSAGE);
    }
}
