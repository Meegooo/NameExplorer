package meegoo.nameExplorer.gui;

import meegoo.nameExplorer.util.GUIHelper;

import javax.swing.*;

import static meegoo.nameExplorer.gui.GUIObjects.*;

public class Initialize {

    static void initTextFields() {

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(97, 61, 266, 20);
        frame.getContentPane().add(textField);
    }

    static void initButtons() {

        button_getInformation = new JButton(GUIHelper.convertToCenteredMultiline("Get \n history"));
        button_getInformation.setBounds(373, 22, 111, 42);
        frame.getContentPane().add(button_getInformation);

        button_clear = new JButton("Clear");
        button_clear.setBounds(373, 78, 111, 23);
        frame.getContentPane().add(button_clear);

    }

    static void initSeparator() {
        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(489, 0, 2, 445);
    }

    static void initTables(){

        tableModel_main = new TableData(Table.data_main, Table.columnNames_main);
        table_output_main = new JTable(tableModel_main);

        scrollPane_table_output_main = new JScrollPane(table_output_main);
        table_output_main.setFillsViewportHeight(true);
        scrollPane_table_output_main.setBounds(10, 110, 474, 320);
        table_output_main.getColumnModel().getColumn(0).setPreferredWidth(200);
        table_output_main.getColumnModel().getColumn(1).setPreferredWidth(74);
        table_output_main.getColumnModel().getColumn(2).setPreferredWidth(200);
        table_output_main.getTableHeader().setReorderingAllowed(false);


        tableModel_additional = new TableData(Table.data_additional, Table.columnNames_additional);
        table_output_additional = new JTable(tableModel_additional);

        scrollPane_table_output_additional = new JScrollPane(table_output_additional);
        table_output_additional.setFillsViewportHeight(true);
        scrollPane_table_output_additional.setBounds(496, 10, 300, 420);
        table_output_additional.getColumnModel().getColumn(0).setPreferredWidth(200);
        table_output_additional.getColumnModel().getColumn(1).setPreferredWidth(290);
        table_output_additional.getTableHeader().setReorderingAllowed(false);

    }

    static void initPopupMenu(){
        popupMenu_main = new JPopupMenu();
        popupMenu_additional = new JPopupMenu();
        menu_copy_main = new JMenuItem("Copy");
        menu_copy_additional = new JMenuItem("Copy");
        menu_copyUUID = new JMenuItem("Copy UUID");

        popupMenu_main.add(menu_copyUUID);
        popupMenu_main.add(menu_copy_main);
        popupMenu_additional.add(menu_copy_additional);
    }

    static void initRadioButtons() {
    	
    	
        radioButton_nickname = new JRadioButton("Nickname");
        radioButton_nickname.setSelected(true);
        radioButton_nickname.setBounds(125, 22, 100, 20);
        frame.getContentPane().add(radioButton_nickname);
        
        radioButton_uuid = new JRadioButton("UUID");
        radioButton_uuid.setBounds(231, 22, 100, 20);
        frame.getContentPane().add(radioButton_uuid);
        
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButton_uuid);
        radioButtonGroup.add(radioButton_nickname);
    	
        




    }
}
