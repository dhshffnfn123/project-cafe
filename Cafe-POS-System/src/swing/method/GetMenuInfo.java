package swing.method;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GetMenuInfo {
	private JTable table;
	private DefaultTableModel model;
	private int index;
	private int sum;
	private StringBuilder str_menu = new StringBuilder();
	
	public GetMenuInfo(JTable table) {
		this.table = table;
		
		model = (DefaultTableModel)table.getModel();
		index = 0;
		
		for (int i = 0; i < model.getRowCount(); i++) {
			int totalCount = (int)model.getValueAt(index, 2);
			int totalPrice = (int)model.getValueAt(index, 3);

			str_menu.append(String.format("%-31s\n", model.getValueAt(index, 1)));
			str_menu.append(String.format("\t\t\t\t\t%5d\t\t%4d\t\t%11d\n", totalPrice / totalCount, totalCount, totalPrice));
			sum += totalPrice;
			index++;								
		}
	}
	
	public StringBuilder getMenuInfo() {
		return str_menu;
	}
	
	public int getSum() {
		return sum;
	}
	
}
