package swing.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChoosePageFrame extends DefaultFrame {
	
	public ChoosePageFrame() {
		setTitle("Choose Page");
		
		String[] btns_name = {"<HTML>직원<br>관리</HTML>", "<HTML>판매<br>등록</HTML>", "<HTML>매출<br>조회</HTML>"};
		ArrayList<JButton> btns = new ArrayList<>();
		
		for (int i = 0; i < btns_name.length; i++) {
			btns.add(new JButton(btns_name[i]));
		}
		
		for (int i = 0; i < btns_name.length; i++) {
			btns.get(i).setBounds(300 * (i + 1), 220, 250, 500);
			btns.get(i).setFont(new Font("맑은 고딕", Font.BOLD|Font.ITALIC, 50));
			btns.get(i).setForeground(Color.WHITE);
			btns.get(i).setBackground(new Color(53, 84, 0));
			btns.get(i).setBackground(new Color(53, 84, 0));
			btns.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals(btns_name[0])) {
						System.out.println("직원 관리 버튼");
					} else if (e.getActionCommand().equals(btns_name[1])) {
						System.out.println("판매 등록 버튼");
					} else {
						System.out.println("매출 조회 버튼");
					}
				}
			});
			
			add(btns.get(i));
		}
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ChoosePageFrame();
	}
}
