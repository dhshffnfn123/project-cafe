package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.model.MenuButtonData;
import swing.frame.OrderFrame;

public class TableAllDelete implements ActionListener {

	JTable table;
	ArrayList<MenuButtonData> tableInfo;

	public TableAllDelete(JTable table, ArrayList<MenuButtonData> tableInfo) {
		this.table = table;
		this.tableInfo = tableInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		tableInfo.removeAll(tableInfo);

		OrderFrame.getTotalmoney().setText(String.valueOf(0));
	}
}