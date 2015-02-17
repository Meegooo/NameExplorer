package meegoo.nameExplorer.gui;


import meegoo.nameExplorer.common.*;
import meegoo.nameExplorer.mojangapi.NamesFromUUID;
import meegoo.nameExplorer.mojangapi.UUIDSFromName;
import meegoo.nameExplorer.storage.Player;
import meegoo.nameExplorer.util.SystemHelper;

import javax.swing.*;
import java.awt.event.*;

import static meegoo.nameExplorer.gui.GUIObjects.*;


public class EventHandler implements ActionListener {

    private static boolean isUUID = false;
    private static String uuid, nickname;

    public static void initHandler() {

        button_getInformation.addActionListener(new EventHandler());
        button_clear.addActionListener(new EventHandler());
        menu_copy_main.addActionListener(new EventHandler());
        menu_copy_additional.addActionListener(new EventHandler());
        menu_copyUUID.addActionListener(new EventHandler());
        radioButton_nickname.addActionListener(new EventHandler());
        radioButton_uuid.addActionListener(new EventHandler());


        table_output_main.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.rowAtPoint(e.getPoint());
                int column = target.columnAtPoint(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON1) onRowClicked(row);
                if (e.getButton() == MouseEvent.BUTTON3) dropMenu(row, column, e, target);
            }
        });

        table_output_additional.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.rowAtPoint(e.getPoint());
                int column = target.columnAtPoint(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON3) dropMenu(row, column, e, target);
            }
        });

//        button_getInformation.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent event){
//                System.out.println("Key Pressed");
//                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
//                    button_getInformation.doClick();
//                }
//            }
//
//        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == button_getInformation) {

            if (!isUUID) {
                Table.updateMainTable(API.getPlayers(textField.getText()));

                if (!UUIDSFromName.wrongNickname) {
                    Table.drawMainOutput();
                    if (currentRow != -1) {
                        Player player = new Player(API.getJSONFromUUID(Table.currentUUIDS.get(currentRow)));
                        Table.updateAdditionalTable(player);
                        Table.drawAdditionalOutput();
                    }
                }
            } else if (isUUID) {
                Player player = new Player(API.getJSONFromUUID(textField.getText()));
                Table.updateMainTable(player);
                if (!NamesFromUUID.wrongUUID) {
                    Table.drawMainOutput();
                    Table.drawAdditionalOutput();
                    table_output_main.changeSelection(0,0,false,false);
                    currentRow = 0;
                }
            }

        }

        if (event.getSource() == button_clear) {
            Table.hideMainOutput();
            uuid = "";
            nickname = "";
            textField.setText(null);
            currentRow = -1;
        }

        if (event.getSource() == menu_copy_main) {
            String copied = (table_output_main.getModel().getValueAt(table_output_main.getSelectedRow(), table_output_main.getSelectedColumn())).toString();
            SystemHelper.copyToClipboard(copied);
        }
        if (event.getSource() == menu_copy_additional) {
            String copied = (table_output_additional.getModel().getValueAt(table_output_additional.getSelectedRow(), table_output_additional.getSelectedColumn())).toString();
            SystemHelper.copyToClipboard(copied);
        }
        if (event.getSource() == menu_copyUUID) {
            String copied = (Table.currentUUIDS.get(table_output_main.getSelectedRow())).toString();
            SystemHelper.copyToClipboard(copied);
        }
        if (event.getSource() == radioButton_nickname) {
            if (isUUID) {
                uuid = textField.getText();
                textField.setText(nickname);
                isUUID = false;
            }
        }
        if (event.getSource() == radioButton_uuid) {
            if (!isUUID) {
                nickname = textField.getText();
                textField.setText(uuid);
                isUUID = true;
            }
        }
    }

    private static int currentRow = -1;

    public static void onRowClicked(int row) {
        if (row != -1) {
            if (currentRow != row) {
                currentRow = row;
                Player player = new Player(API.getJSONFromUUID(Table.currentUUIDS.get(row)));
                Table.updateAdditionalTable(player);
                Table.drawAdditionalOutput();
            } else {
                Table.hideAdditionalOutput();
                currentRow = -1;
            }

        }
    }

    public static void dropMenu(int row, int column, MouseEvent e, JTable target) {


        if (!target.isRowSelected(row) || !target.isColumnSelected(column)) {
            if (row != -1) target.changeSelection(row, column, false, false);
        }
        if (target.equals(table_output_main)) popupMenu_main.show(e.getComponent(), e.getX(), e.getY());
        if (target.equals(table_output_additional)) popupMenu_additional.show(e.getComponent(), e.getX(), e.getY());
    }


}
