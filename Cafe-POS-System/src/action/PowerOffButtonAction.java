package action;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class PowerOffButtonAction implements ActionListener {

	private JFrame frame;
	
	public PowerOffButtonAction(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.PLAIN, 12));
		int result = JOptionPane.showConfirmDialog(null, "시스템을 종료하시겠습니까?", "SYSTEM", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
		
	}
	
}
