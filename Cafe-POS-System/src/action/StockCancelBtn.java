package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class StockCancelBtn implements ActionListener{

	private JFrame frame;
	
	
	public StockCancelBtn(JFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		
	}

}
