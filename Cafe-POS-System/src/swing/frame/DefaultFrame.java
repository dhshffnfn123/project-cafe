package swing.frame;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame {
	
	public DefaultFrame() {
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(1500, 1000);
		setSize(1300, 800);
		setResizable(false);
		setVisible(true);
	}
	
}
