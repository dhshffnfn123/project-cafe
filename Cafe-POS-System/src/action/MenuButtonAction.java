package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private int index;
	private int originalPrice;
	private boolean check;
	
	public MenuButtonAction(JTable table, String name, int price) {
		this.table = table;
		this.name = name;
		this.price = price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
	      model = (DefaultTableModel) table.getModel();
	      int row = table.getRowCount(); 
	      
	      if (!check) {
	         OrderFrame.GettableInfo().add(new MenuButtonData(row, name, 1, price));
	         model.addRow(OrderFrame.GettableInfo().get(row).getTableRow());
	      } 
	     
	      check = false;
	      price = originalPrice;
	      	      
	      int findIndex = 0;
	      if (OrderFrame.GettableInfo().size() != 0) {
	         for (int i = 0; i < OrderFrame.GettableInfo().size(); i++) {
	            if (OrderFrame.GettableInfo().get(i).getMenuName().equals(name)) {
	               findIndex = i;
	               
	               quantity = OrderFrame.GettableInfo().get(findIndex).getQty();
	               price = OrderFrame.GettableInfo().get(findIndex).getMenuPrice(); 

	               model.setValueAt(quantity, findIndex, 2);
	               model.setValueAt(price, findIndex, 3);
	               
	               originalPrice = price / quantity;
	               price += originalPrice;
	               quantity++;
	               
	               OrderFrame.GettableInfo().get(findIndex).setQty(quantity);
	               OrderFrame.GettableInfo().get(findIndex).setMenuPrice(price);
	               
	               check = true;
	               model.fireTableDataChanged();
	            } 
	         }
	      }
	}
}