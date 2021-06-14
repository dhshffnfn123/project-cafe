package action;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

import jdbc.hikari.HikariCP;
import swing.frame.DefaultFrame;

public class Password_tf_Listener implements ActionListener {
	
	private String cbname;
	char[] password;
	private String sql = "SELECT * FROM employees_table WHERE employee_name = '" + cbname + "'";
	private Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	private JFrame frame;
	private JPasswordField pwf;
	
	
	
	
	
	public Password_tf_Listener(JPasswordField pwf, String cbname, JFrame frame) {
		this.pwf = pwf;
		this.cbname = cbname;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			password = pwf.getPassword();
			String pass =  new String(password);
			System.out.println(pass);
			while (rs.next()) {
			

				System.out.println(pass);
				System.out.println(rs.getString(3)); 
				if(pass.equals(rs.getString(3))) {
					System.out.println("로그인 성공");
					System.out.println("얻어온 비밀번호 = " + password);
					frame.setVisible(false);
					//new OrderView().setVisible(true);
					break;
				}
			} // while
	
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
		
	}

}
