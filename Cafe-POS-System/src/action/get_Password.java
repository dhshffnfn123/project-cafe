package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JPasswordField;

public class get_Password implements ActionListener {
	private String password;
	private JPasswordField pwf;
	
	public get_Password(JPasswordField pwf) {
		this.pwf = pwf;
		
	}
	
	public String pass() {
		return password;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		password = new String(this.pwf.getPassword());
		System.out.println(password);
		
		
	}
}
