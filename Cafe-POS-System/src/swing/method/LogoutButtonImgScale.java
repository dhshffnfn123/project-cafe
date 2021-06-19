package swing.method;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LogoutButtonImgScale extends JButton {

	private JButton logoutBtn;
	private ImageIcon changeIcon;
	
	public LogoutButtonImgScale() {
		ImageIcon icon = new ImageIcon("./image/logoutbutton.png");

		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		changeIcon = new ImageIcon(changeImg);

		logoutBtn = new JButton(changeIcon);
		logoutBtn.setBorderPainted(false);
		logoutBtn.setFocusPainted(false);
	}
	
	public JButton getLogoutBtn() {
		return logoutBtn;
	}

}
