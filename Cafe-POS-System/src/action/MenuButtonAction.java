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
	         OrderFrame.GettableInfo().add(new MenuButtonData(row, name, 1, price));
	         model.addRow(OrderFrame.GettableInfo().get(row).getTableRow());
	         OrderFrame.GetMenuHash().put(name, price);
	      }else {
	    	  price = originalPrice;
	    	  for (int i = 0; i < OrderFrame.GettableInfo().size(); i++) {
		            if (OrderFrame.GettableInfo().get(i).getMenuName().equals(name)) {
		               
		               
		               quantity = OrderFrame.GettableInfo().get(i).getQty();
		               price = OrderFrame.GettableInfo().get(i).getMenuPrice(); 

		               model.setValueAt(quantity, i, 2);
		               model.setValueAt(price, i, 3);
		               
		               originalPrice = price / quantity;
		               price += originalPrice;
		               quantity++;
		               
		               OrderFrame.GettableInfo().get(i).setQty(quantity);
		               OrderFrame.GettableInfo().get(i).setMenuPrice(price);
		               
		               model.fireTableDataChanged();
		            } 
		         }
	    	  
	      }
	    
	}

}
