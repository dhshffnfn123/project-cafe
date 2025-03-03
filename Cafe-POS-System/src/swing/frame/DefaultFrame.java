package swing.frame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame {

	private Toolkit kit;
	private Image image;
	
	public DefaultFrame() {
		kit = Toolkit.getDefaultToolkit();
		image = kit.getImage("./Image/Icon.png");
		setIconImage(image);
		
		setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setFont(new Font("���� ����", Font.BOLD, 17));
		setSize(1500, 1000);
		setLocation(450, 200);
		setResizable(false);
		setVisible(true);
	}
	
}
