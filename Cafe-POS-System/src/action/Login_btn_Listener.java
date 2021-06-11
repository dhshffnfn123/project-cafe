package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Login_btn_Listener implements ActionListener {
	JButton login_btn;
	String cbname;
	String sql = "SELECT * FROM employees_table WHERE employee_name = '" + cbname + "'";
	
	public Login_btn_Listener (JButton login_btn, String cbname) {
		this.login_btn = login_btn;
		this.cbname = cbname;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
