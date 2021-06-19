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
	
	public BackButtonMouseActionForOrderFrame(JFrame frame, JTable table) {
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

		new ChoosePageFrame();
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
