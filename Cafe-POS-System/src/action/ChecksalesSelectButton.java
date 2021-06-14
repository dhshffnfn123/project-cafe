package action;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

public class ChecksalesSelectButton implements ActionListener {
	
	private JTable table;
	private ArrayList<TextField> ck_fields;
	private String order_time;
	private String order_total;
	
	public ChecksalesSelectButton(JTable table, ArrayList<TextField> ck_fields) {
		this.table = table;
		this.ck_fields = ck_fields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		order_time = (String) table.getModel().getValueAt(row, 0);
		order_total = (String) table.getModel().getValueAt(row, 2);
		
	}
}
