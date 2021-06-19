package action;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import swing.frame.LoginFrame;
import swing.method.LoginEmployeeInfoLabel;

public class LogoutButtonAction implements MouseListener {

	private JFrame frame;
	
	public LogoutButtonAction(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		UIManager.put("OptionPane.messageFont",new Font("맑은 고딕",Font.PLAIN,12));
		int result = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			LoginEmployeeInfoLabel.getLabel().setText("");
			frame.dispose();
			new LoginFrame();
		} 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
