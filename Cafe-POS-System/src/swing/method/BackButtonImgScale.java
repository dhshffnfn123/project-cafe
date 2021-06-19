package swing.method;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackButtonImgScale extends JButton {

	private JButton backBtn;
	private ImageIcon changeIcon;
	
	public BackButtonImgScale() {
		ImageIcon icon = new ImageIcon("./image/backbutton.png");
		
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		changeIcon = new ImageIcon(changeImg);
		
		backBtn = new JButton(changeIcon);
		backBtn.setBorderPainted(false);
		backBtn.setFocusPainted(false);
	}
	
	public JButton getBackBtn() {
		return backBtn;
	}
}

