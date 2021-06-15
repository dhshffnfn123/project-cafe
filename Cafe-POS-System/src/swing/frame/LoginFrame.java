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

import action.LoginButtonListener;
import action.LoginComboBoxListener;
import action.PasswordTextFieldListener;



import jdbc.method.Login_combox_data;


public class LoginFrame extends DefaultFrame {
	
	JPanel panel, login_panel, test_panel;
	JScrollPane scrollPane;
	ImageIcon icon;
	JComboBox combox;
	JPasswordField pwf;
	Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);
	JButton login_btn;
	String cbname, password;
	static int count = 0;
	
	
	public LoginFrame() {
		
		  
		icon = new ImageIcon("./image/커피사진.jpg");
			
		// ============================================= 배경화면 이미지 삽입
		
	
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);
		panel.setLayout(null);
			
			
			// ============================================= 로그인 패널
			login_panel = new JPanel();
			login_panel.setBounds(100, 600, 400, 200);
			login_panel.setBackground(new Color(255, 255, 255, 255));
			login_panel.setBorder(new TitledBorder
					(new LineBorder(Color.DARK_GRAY,10),"Log_in")); // 패널의 테두리
			
			login_panel.setLayout(null);
			
			
			panel.add(login_panel);
			
			// ============================================= 로그인 버튼
			login_btn = new JButton("LOGIN");
			login_btn.setBackground(new Color (0,0,0));
			login_btn.setFont(font1);
			login_btn.setBounds(130,150,150,30);
			login_btn.setForeground(Color.white);
			
			login_panel.add(login_btn);
			
			// ============================================= 콤보 박스
			
			
			combox = new Login_combox_data().getComboBox();
			
			combox.setBounds(80,30, 250, 35);
			combox.setFont(font1);
			
			login_panel.add(combox);
			

			// ============================================= 텍스트필드
			pwf = new JPasswordField(5);
			pwf.setBounds(80, 80 , 250, 35);
			pwf.setFont(font1);
			
			login_panel.add(pwf);
			
			
			
			// passwordfield 엔터 누르기
			pwf.addActionListener(new PasswordTextFieldListener(combox, cbname, pwf, this));
			// 로그인 버튼 누르기
			login_btn.addActionListener(new LoginButtonListener(combox, pwf, cbname, this));
			
			
			
			
			
			
			setVisible(true);
			
			
			
		 } // login _Frame
			 
		 public static void main(String args[]) {
			new LoginFrame();
			 
		 }
		 
		 


}
