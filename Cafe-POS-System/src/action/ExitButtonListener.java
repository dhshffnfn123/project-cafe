package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class ExitButtonListener implements ActionListener {
	private JFrame frame;
	public ExitButtonListener(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		
	}

}
