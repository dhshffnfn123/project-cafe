package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import swing.frame.ChoosePageFrame;

public class BackButtonMouseAction implements MouseListener {

	private JFrame frame;
	private String order_name, grade;
	
	public BackButtonMouseAction(JFrame frame, String grade, String order_name) {
		this.frame = frame;
		this.order_name = order_name;
		this.grade = grade;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		frame.dispose();
		new ChoosePageFrame(grade, order_name);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
