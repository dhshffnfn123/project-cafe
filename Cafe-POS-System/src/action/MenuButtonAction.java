package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.model.MenuButton;
import jdbc.model.MenuButtonData;
import swing.frame.OrderFrame;

public class MenuButtonAction implements ActionListener {

	private DefaultTableModel model;
	private JTable table;
	private String name;
	private int price;
	private int quantity;
	private int totalPrice;
	private int test = 0;

	public MenuButtonAction(JTable table, String name, int price) {
		this.table = table;
		this.name = name;
		this.price = price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		model = (DefaultTableModel) table.getModel();
		int row = table.getRowCount();

		if (!OrderFrame.GetMenuHash().containsKey(name)) {
			OrderFrame.GettableInfo().add(new MenuButtonData(row + 1, name, 1, price));
			model.addRow(OrderFrame.GettableInfo().get(row).getTableRow());

			OrderFrame.GetMenuHash().put(name, price);

			OrderFrame.getTotalmoney().setText(String.valueOf(price));
			model.fireTableDataChanged();
		}

		for (int i = 0; i < OrderFrame.GettableInfo().size(); i++) {
			if (OrderFrame.GettableInfo().get(i).getMenuName().equals(name)) {
				quantity = OrderFrame.GettableInfo().get(i).getQty();
				totalPrice = OrderFrame.GettableInfo().get(i).getTotalPrice();

				model.setValueAt(quantity, i, 2);
				model.setValueAt(totalPrice, i, 3);

				quantity++;

				OrderFrame.GettableInfo().get(i).setQty(quantity);
				model.fireTableDataChanged();
			}
		}

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