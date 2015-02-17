package meegoo.nameExplorer.gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableData extends AbstractTableModel {

    ArrayList<Object[]> dataList;

    String[] header;


    public TableData(Object[][] data, String[] header) {
        this.header = header;
        dataList = new ArrayList<Object[]>();

        for (int i = 0; i< data.length; i++) {
            dataList.add(data[i]);
        }
    }

    public void changeTableData(Object[][] data) {

        dataList=null;
        dataList = new ArrayList<Object[]>();

        for (int i = 0; i< data.length; i++) {
            dataList.add(data[i]);
        }
    }

    public String getColumnName(int index) {
        return header[index];
    }


    @Override
    public int getRowCount() {
        return dataList.size();
    }
    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataList.get(rowIndex)[columnIndex];
    }
}
