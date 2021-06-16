package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuPlusButton implements ActionListener {

	JTable table;
	JLabel totalmoney;
	DefaultTableModel model;
	static int pluscount =0;
	static int plusprice =0;
	int i= 1;
	int tablemoney;
	
	public MenuPlusButton(JTable table, JLabel totalmoney ,int tablemoney) {
		this.table = table;
		this.totalmoney = totalmoney;
		this.tablemoney = tablemoney;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() != -1) {
			int row = table.getSelectedRow();
			model = (DefaultTableModel) table.getModel();
			pluscount = Integer.parseInt((String) model.getValueAt(row, 2));
			plusprice = Integer.parseInt((String) model.getValueAt(row, 3));
			
			model.setValueAt(pluscount+i, row, 2);
			model.setValueAt((plusprice*(pluscount+i)), row, 3);
			
			tablemoney = plusprice*(pluscount+i);
			totalmoney.setText(String.format("%s", tablemoney));
		}
	}
}
