package meegoo.nameExplorer.gui;

import meegoo.nameExplorer.storage.Player;
import meegoo.nameExplorer.util.CustomList;
import meegoo.nameExplorer.util.TimeHelper;

import java.util.List;
import java.util.UUID;

import static meegoo.nameExplorer.gui.GUIObjects.*;
import static meegoo.nameExplorer.gui.GUIObjects.frame;

public class Table {

    static boolean isMainTableVisible = false;
    static boolean isAdditionalTableVisible = false;
    static List<UUID> currentUUIDS;

    static Object[][] data_main = {
            {"DummyName1", "DummyChanges1", "DummyCurrNickname1"},
            {"DummyName2", "DummyChanges2", "DummyCurrNickname2"},
            {"DummyName3", "DummyChanges3", "DummyCurrNickname3"},

    };
    static Object[][] data_additional = {
            {"DummyName1", "DummyTime1"},
            {"DummyName2", "DummyTime2"},
            {"DummyName3", "DummyTime3"},

    };

    static String[] columnNames_main = {"First Nickname", "Changes", "Current Nickname"};
    static String[] columnNames_additional = {"Nickname", "Time",};




    static void drawMainOutput() {
        if (!isMainTableVisible) {
            frame.setSize(500,470);
            frame.getContentPane().add(scrollPane_table_output_main);
            isMainTableVisible = true;
        }

    }

    static void hideMainOutput() {
        hideAdditionalOutput();
        if (isMainTableVisible) {
            frame.setSize(500, 160);
            frame.remove(scrollPane_table_output_main);
            frame.setVisible(true);
            hideAdditionalOutput();
        }
        isMainTableVisible = false;
    }

    static void drawAdditionalOutput() {
        if (!isAdditionalTableVisible && isMainTableVisible) {
            frame.setSize(820,470);
            frame.getContentPane().add(separator);
            frame.getContentPane().add(scrollPane_table_output_additional);
        }
        isAdditionalTableVisible = true;

    }
    static void hideAdditionalOutput() {
        if (isAdditionalTableVisible && isMainTableVisible) {
            frame.setSize(500, 470);
            frame.remove(scrollPane_table_output_additional);
            frame.remove(separator);
            frame.setVisible(true);
        }
        isAdditionalTableVisible = false;
    }

    static void updateMainTable(List<Player> players) {
        currentUUIDS = new CustomList<>();
        data_main = new Object [players.size()][3];
        int i=0;
        for (Player player : players) {
            currentUUIDS.add(player.getUUID());
            data_main[i][0] = player.get(Player.NAME, 0).toString();
            data_main[i][1] = player.getSize()-1;
            data_main[i][2] = player.get(Player.NAME, player.getSize()-1).toString();
            i++;
        }
        tableModel_main.changeTableData(data_main);
        tableModel_main.fireTableDataChanged();

    }

    static void updateMainTable(Player player) {
        currentUUIDS = new CustomList<>();
        data_main = new Object [1][3];
        currentUUIDS.add(player.getUUID());
        data_main[0][0] = player.get(Player.NAME, 0).toString();
        data_main[0][1] = player.getSize()-1;
        data_main[0][2] = player.get(Player.NAME, player.getSize()-1).toString();
        tableModel_main.changeTableData(data_main);
        tableModel_main.fireTableDataChanged();
        updateAdditionalTable(player);
    }

    static void updateAdditionalTable(Player player) {
        data_additional = new Object[player.getSize()][2];
        for (int i = 0; i<player.getSize(); i++) {
            String time = ((Long)player.get(Player.TIME, i) == 0) ? ("First Nickname") : (TimeHelper.parseUnixTimestampIntoString(((Long)player.get(Player.TIME, i))));
            data_additional[i][0] = player.get(Player.NAME, i);
            data_additional[i][1] = time;
        }
        tableModel_additional.changeTableData(data_additional);
        tableModel_additional.fireTableDataChanged();

    }

}
