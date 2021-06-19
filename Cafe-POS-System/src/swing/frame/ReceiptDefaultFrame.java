package swing.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import swing.method.ReceiptJScrollPane;
import swing.method.ReceiptLabel;

public class ReceiptDefaultFrame extends DefaultFrame {
	public ReceiptDefaultFrame() {
		setTitle("Receipt");
		new ReceiptJScrollPane();
		setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(650, 1000);
		setLocation(1000, 250);
		setResizable(false);
		setVisible(true);
	}
}
