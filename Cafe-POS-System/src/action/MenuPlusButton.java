package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuPlusButton implements ActionListener {

	JTable table;
	int price;
	int quantity;
	int originalPrice;
	int tablemoney;
	JLabel totalmoney;

	public MenuPlusButton(JTable table ) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int row = table.getSelectedRow();

		if (row != -1) {
			quantity = Integer.parseInt(String.valueOf(model.getValueAt(row, 2)));
			price = Integer.parseInt(String.valueOf(model.getValueAt(row, 3)));
			originalPrice = price / quantity;

			quantity++;
			price += originalPrice;
			
			model.setValueAt(quantity, row, 2);
			model.setValueAt(price, row, 3);
			model.fireTableDataChanged();
		}

	}
}
