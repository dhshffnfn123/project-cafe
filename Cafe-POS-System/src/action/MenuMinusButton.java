package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import swing.frame.OrderFrame;

public class MenuMinusButton implements ActionListener {

	JTable table;
	DefaultTableModel model;
	JLabel totalmoney;
	int price;
	int quantity;
	int originalPrice;
	int tablemoney;
	int test;

	public MenuMinusButton(JTable table, JLabel totalmoney, int tablemoney) {
		this.table = table;
		this.totalmoney = totalmoney;
		this.tablemoney = tablemoney;
	}

	public void actionPerformed(ActionEvent e) {
		model = (DefaultTableModel) table.getModel();
		int row = table.getSelectedRow();

		if (row != -1) {
			quantity = Integer.parseInt(String.valueOf(model.getValueAt(row, 2)));

			if (quantity == 1) {
				// 선택된 행만 지운다. model.setRowCount(0);는 1개인 음료도 다 지워버림
				OrderFrame.GetMenuHash().remove(OrderFrame.GettableInfo().get(row).getMenuName());
				OrderFrame.GettableInfo().remove(row);
				model.removeRow(row);
				price = 0;
				OrderFrame.getTotalmoney().setText(String.valueOf(price));

			} else if (quantity != 1) {

				price = Integer.parseInt(String.valueOf(model.getValueAt(row, 3)));
				originalPrice = price / quantity;

				quantity--;
				price -= originalPrice;

				OrderFrame.GettableInfo().get(row).setQty(quantity);
				OrderFrame.GettableInfo().get(row).setMenuPrice(price);

				model.setValueAt(quantity, row, 2);
				model.setValueAt(price, row, 3);

			}

			if (model.getRowCount() >= 1) {
				for (int i = 0; i < model.getRowCount(); ++i) {
					model.setValueAt(i, i, 0);
					test += Integer.parseInt((String.valueOf(model.getValueAt(i, 3))));
				}
				DecimalFormat formatter = new DecimalFormat("###,###");

				OrderFrame.getTotalmoney().setText(String.valueOf(formatter.format(test)));
				test = 0;
			}
		}

	}

}
