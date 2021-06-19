package swing.method;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PowerOffButtonImgScale extends JButton {
	
	private JButton powerOffBtn;
	private ImageIcon changeIcon;
	
	public PowerOffButtonImgScale() {
		ImageIcon icon = new ImageIcon("./image/poweroffbutton.png");

		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		changeIcon = new ImageIcon(changeImg);

		powerOffBtn = new JButton(changeIcon);
		powerOffBtn.setBorderPainted(false);
		powerOffBtn.setFocusPainted(false);
	}
	
	public JButton getPowerOffBtn() {
		return powerOffBtn;
	}

}
