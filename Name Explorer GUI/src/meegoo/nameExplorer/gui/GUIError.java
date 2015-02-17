package meegoo.nameExplorer.gui;

import javax.swing.*;

public class GUIError {
    public GUIError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
