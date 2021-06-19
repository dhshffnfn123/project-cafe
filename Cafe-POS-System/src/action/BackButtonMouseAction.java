package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import swing.frame.ChoosePageFrame;

public class BackButtonMouseAction implements MouseListener {

	private JFrame frame;

	public BackButtonMouseAction(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		frame.dispose();
		new ChoosePageFrame();
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
