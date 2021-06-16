package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrdertableDeleteAction implements ActionListener {

	JTable table;
	DefaultTableModel model;
	JLabel totalmoney;
	int price;
	int totalprice;
	int count;
	int tablemoney;

	public OrdertableDeleteAction(JTable table, JLabel totalmoney, int tablemoney) {
		this.table = table;
		this.totalmoney = totalmoney;
		this.tablemoney = tablemoney;
	}

	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() != -1) {

			int row = table.getSelectedRow();
			model = (DefaultTableModel) table.getModel();
			count = Integer.parseInt((String) model.getValueAt(row, 2));
			price = Integer.parseInt((String) model.getValueAt(row, 3));

			model.removeRow(table.getSelectedRow());
			
	

		}

	}
}
