package meegoo.nameExplorer.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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

        
        frame.setVisible(true);
        textField.requestFocus();
        frame.getRootPane().setDefaultButton(button_getInformation);

    }
}
