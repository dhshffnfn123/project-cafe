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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import jdbc.hikari.HikariCP;
import swing.frame.DefaultFrame;

public class LoginButtonListener implements ActionListener {
	private JFrame frame;
	private String cbname, getname, getpassword, pass;
	char[] password;
	private String sql = "SELECT * FROM employees_table WHERE employee_name = ?";
	private JComboBox combox;
	private JPasswordField pwf;
	private Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	private int exit_count = 0;

	public LoginButtonListener(JComboBox combox, JPasswordField pwf, String cbname, JFrame frame) {
		this.combox = combox;
		this.pwf = pwf;
		this.cbname = cbname;
		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cbname = combox.getSelectedItem().toString();

		password = pwf.getPassword();
		pass = new String(password); // 입력한 값

		try (Connection conn = HikariCP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, cbname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				getname = rs.getString(2);
				getpassword = rs.getString(3);
				System.out.println(getpassword);
				System.out.println(getname);

			}

			rs.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		if (pass.equals(getpassword) && cbname.equals(getname)) {

			System.out.println("로그인 성공");
			System.out.println("얻어온 비밀번호 = " + pass);
			frame.dispose();

		} else {
			UIManager.put("OptionPane.messageFont", font1);
			JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다", "SYSTEM", JOptionPane.CANCEL_OPTION);
		}

	}

}
