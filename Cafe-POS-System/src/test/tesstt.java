package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class tesstt extends JFrame {

	public tesstt() {
		
	setLayout(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 500);
	setResizable(false);
	setVisible(true);
	
	JPasswordField pwf = new JPasswordField(5);
	pwf.setBounds(80, 80 , 250, 35);
	add(pwf);
	
	pwf.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String password = new String(pwf.getPassword());
			System.out.println(password);
			
		}
	});
	
	
	}
	
	public static void main(String[] args) {
		new tesstt();
		
	}

	
}
