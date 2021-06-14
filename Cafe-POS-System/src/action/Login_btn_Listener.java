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
import javax.swing.UIManager;

import jdbc.hikari.HikariCP;
import swing.frame.DefaultFrame;


public class Login_btn_Listener  implements ActionListener {
	private JFrame frame;
	private String cbname, password;
	private String sql = "SELECT * FROM employees_table WHERE employee_name = '" + cbname + "'";
	
	private JPasswordField pwf;
	private Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	private int exit_count = 0 ;
	
	public Login_btn_Listener (JPasswordField pwf, String cbname, JFrame frame) {
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
			while (rs.next()) {
				this.pwf.addActionListener(new get_Password(pwf));
				System.out.println(password);
				System.out.println(rs.getString(3));
				if(password.equals(rs.getString(3))) {
					System.out.println("로그인 성공");
					System.out.println("얻어온 비밀번호 = " + password);
					frame.setVisible(false);
					//new OrderView().setVisible(true);
					break;
				}
				
			}
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
	}
}
