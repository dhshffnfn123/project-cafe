package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swing.frame.OrderFrame;

public class MenuPlusButton implements ActionListener {

	JTable table;
	int price;
	int quantity;
	int originalPrice;
	int tablemoney;
	JLabel totalmoney;
	int test;

	public MenuPlusButton(JTable table) {
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

			OrderFrame.GettableInfo().get(row).setQty(quantity);
			OrderFrame.GettableInfo().get(row).setMenuPrice(price);

			model.setValueAt(quantity, row, 2);
			model.setValueAt(price, row, 3);

			if (model.getRowCount() >= 1) {
				for (int i = 0; i < model.getRowCount(); ++i) {
					model.setValueAt(i + 1, i, 0);
					test += Integer.parseInt((String.valueOf(model.getValueAt(i, 3))));
				}
				DecimalFormat formatter = new DecimalFormat("###,###");

				OrderFrame.getTotalmoney().setText(String.valueOf(formatter.format(test)));
				test = 0;
			}
		}
	}
}