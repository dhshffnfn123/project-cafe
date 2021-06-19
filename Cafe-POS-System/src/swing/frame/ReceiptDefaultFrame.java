package swing.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import swing.method.ReceiptJScrollPane;
import swing.method.ReceiptLabel;

public class ReceiptDefaultFrame extends JFrame {
	public ReceiptDefaultFrame() {
		new ReceiptJScrollPane();
		setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(650, 1000);
		setLocation(700, 0);
		setResizable(false);
		setVisible(true);
	
	}
}
