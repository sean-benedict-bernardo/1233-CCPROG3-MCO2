package view.common.auxiliary;

import javax.swing.JOptionPane;

/**
 * The Alert class is a wrapper of common showMessageDialog calls
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
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
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
