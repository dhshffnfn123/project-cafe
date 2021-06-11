package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class GetTableInfoForMouse implements MouseListener {

	private JTable table;
	private String employee_id;
	private String employee_name;
	private String employee_password;
	private String employee_grade;
	
	public GetTableInfoForMouse(JTable table) {
		this.table = table;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		employee_id = (String)table.getModel().getValueAt(row, 0);
		employee_name = (String)table.getModel().getValueAt(row, 1);
		employee_password = (String)table.getModel().getValueAt(row, 2);
		employee_grade = (String)table.getModel().getValueAt(row, 3);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
