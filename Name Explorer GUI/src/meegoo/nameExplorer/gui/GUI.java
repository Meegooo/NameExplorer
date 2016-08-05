package meegoo.nameExplorer.gui;

import javax.swing.*;

import static meegoo.nameExplorer.gui.GUIObjects.*;


public class GUI {




    public static void initialize() {

    	frame = new JFrame("Name Explorer");
    	frame.setSize(500, 160);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        

        Initialize.initTextFields();
        Initialize.initButtons();
        Initialize.initSeparator();
        Initialize.initTables();
        Initialize.initPopupMenu();
        Initialize.initRadioButtons();
        EventHandler.initHandler();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        frame.setVisible(true);
        textField.requestFocus();
        frame.getRootPane().setDefaultButton(button_getInformation);

    }
}
