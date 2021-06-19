package swing.method;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.method.RoadProductInfo;
import jdbc.method.RoadRtdInfo;
import jdbc.method.RoadStockCount;
import swing.view.ProductView;

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
			int total_count = (int)model.getValueAt(index, 2);
			int total_price = (int)model.getValueAt(index, 3);
			String menu_name = (String) model.getValueAt(index, 1);
			str_menu.append(String.format("%-31s\n", menu_name));
			str_menu.append(String.format("\t\t\t\t\t%5d\t\t%4d\t\t%11d\n", total_price / total_count, total_count, total_price));

			sum += total_price;
			index++;
			new RoadProductInfo(menu_name, total_count);
			new RoadRtdInfo(menu_name, total_count);
		}
	}
	public StringBuilder getMenuInfo() {
		return str_menu;
	}
	public int getSum() {
		return sum;
	}
	
}
