package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuButtonAction implements ActionListener {
	String name;
	int price;
	DefaultTableModel model;
	static int sum;
	JTable table;
	JLabel totalmoney;
	int count = 0;
	String testName;
	String testPrice;

	public MenuButtonAction(String name, int price, JTable table, JLabel totalmoney) {
		this.name = name;
		this.price = price;
		this.table = table;
		this.totalmoney = totalmoney;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//MenuButtonData a1 = new MenuButtonData();
		model = (DefaultTableModel) table.getModel();
		
		ArrRow
		
		// 클래스로 구현해서 접근 MenuButtonData 클래스 만듬 // 인스턴스.get();
		String[] row = new String[4]; 
		row[0] = Integer.toString(model.getRowCount() + 1);
		row[1] = name;
		row[2] = "1";
		row[3] = Integer.toString(price);
		model.addRow(row);

		String str = Integer.toString(price);
		sum += (Integer.parseInt(str));
		totalmoney.setText(String.format("%s", sum));

		// ArrayList 로 구현하기!
		int row_cnt = table.getRowCount();
		for (int i = 0; i < row_cnt; i++) {
			model.setValueAt(i, i, 0);
			model.fireTableDataChanged();
		}

		for (int j = 0; j < row_cnt; j++) {
			if (name.equals(model.getValueAt(j, 1))) {
				count++;

			}
			if (count >= 2) {
				testName = String.valueOf(model.getValueAt(j, 1));
				testPrice = String.valueOf(model.getValueAt(j, 3));

				model.removeRow(j);

				model.setValueAt(count, j, 2);
				model.setValueAt((Integer.parseInt(testPrice) * count), j, 3);

			}
			if (count == 3) {
				break;
			}
		}

	}
}


