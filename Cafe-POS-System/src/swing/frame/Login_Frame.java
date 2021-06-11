package swing.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sun.tools.javac.Main;

public class Login_Frame extends DefaultFrame {
	
	JPanel panel, login_panel;
	JScrollPane scrollPane;
	ImageIcon icon;
	JComboBox combox;
	JPasswordField pwf;
	Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);
	JButton login_btn;
	String cbname;
	static int count = 0;
	int exit_count = 0 ;
	
	public Login_Frame() {
		icon = new ImageIcon("./image/커피사진.jpg");
		
		
		
		// ============================================= 배경화면 이미지 삽입
		 panel = new JPanel() {	
			 public void paintComponent(Graphics g) {

					g.drawImage(icon.getImage(), 0, 0, null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			
			scrollPane = new JScrollPane(panel);
			setContentPane(scrollPane);
			
			login_panel = new JPanel();
			login_panel.setBounds(100, 600, 400, 200);
			login_panel.setBackground(new Color(255, 255, 255, 255));
			login_panel.setBorder(new TitledBorder
					(new LineBorder(Color.DARK_GRAY,10),"Log_in")); // 패널의 테두리
			
			panel.setLayout(null);
			login_panel.setLayout(null);
			
			
			panel.add(login_panel);
			
			
			
			
			
			
			
			
			
			
			
		 } // login _Frame
			 
		 public static void main(String args[]) {
			 
		 }
		 
		 


}
