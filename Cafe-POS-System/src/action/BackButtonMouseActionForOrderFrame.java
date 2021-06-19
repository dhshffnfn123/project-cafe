package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swing.frame.ChoosePageFrame;
import swing.frame.OrderFrame;

public class BackButtonMouseActionForOrderFrame implements MouseListener {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String order_name, grade;
	public BackButtonMouseActionForOrderFrame(JFrame frame, JTable table, String grade, String order_name) {
		this.order_name = order_name;
		this.grade = grade;
		this.frame = frame;
		this.table = table;
		this.model = (DefaultTableModel)table.getModel();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		OrderFrame.GettableInfo().removeAll(OrderFrame.GettableInfo());
		OrderFrame.GetMenuHash().clear();
		OrderFrame.getTotalmoney().setText(String.valueOf(0));

		frame.dispose();

		new ChoosePageFrame(grade, order_name);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	
}
