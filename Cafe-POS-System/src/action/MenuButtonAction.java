package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuButtonAction implements ActionListener{
	String name ;
	int price;
	DefaultTableModel model;
	static int sum ;
	JTable table;
	JLabel totalmoney;
	
	public MenuButtonAction(String name , int price , JTable table, JLabel totalmoney) {
		this.name = name;
		this.price = price;
		this.table = table;
		this.totalmoney = totalmoney;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		model = (DefaultTableModel) table.getModel();
		
		String[] row = new String[4];
		row[0] = "1";
		row[1] = name;
		row[2] = "1";
		row[3] = Integer.toString(price);
		model.addRow(row);
		
		String str = Integer.toString(price);
 	   	
		sum += (Integer.parseInt(str));
		
 	    totalmoney.setText(String.format("%s", sum));
		
	}
}
