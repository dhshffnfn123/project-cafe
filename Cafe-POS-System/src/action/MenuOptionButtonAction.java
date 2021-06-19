package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.model.MenuButtonData;
import swing.frame.OrderFrame;

public class MenuOptionButtonAction implements ActionListener {

	private DefaultTableModel model;
	private JTable table;
	private String name;
	private int price;
	private int quantity = 1;
	private int totalPrice;
	int test = 0;

	public MenuOptionButtonAction(JTable table, String name, int price) {
		this.table = table;
		this.name = name;
		this.price = price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model = (DefaultTableModel) table.getModel();
		int row = table.getRowCount();

		if (row != -1) {
			OrderFrame.GettableInfo().add(new MenuButtonData(row + 1, name, 1, price));
			model.addRow(OrderFrame.GettableInfo().get(row).getOptionRow());

			OrderFrame.GetMenuHash().put(name, price);

			OrderFrame.getTotalmoney().setText(String.valueOf(price));
			model.fireTableDataChanged();
		}

		if (model.getRowCount() >= 1) {
			
			for (int i = 0; i < model.getRowCount(); ++i) {
				model.setValueAt(i + 1, i, 0);
				
				model.setValueAt("¤¤" + name, row, 1);
				model.setValueAt(quantity, row, 2);
				model.setValueAt(price, row, 3);
				
				test += Integer.parseInt((String.valueOf(model.getValueAt(i, 3))));
			}
			
			DecimalFormat formatter = new DecimalFormat("###,###");

			OrderFrame.getTotalmoney().setText(String.valueOf(formatter.format(test)));
			test = 0;
		}

	}

}
