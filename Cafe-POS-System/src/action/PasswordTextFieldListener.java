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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

import jdbc.hikari.HikariCP;
import jdbc.method.GetEmployeeGrade;
import swing.frame.ChoosePageFrame;
import swing.frame.DefaultFrame;
import swing.method.LoginEmployeeInfoLabel;
import swing.method.ReceiptLabel;

public class PasswordTextFieldListener implements ActionListener {

	private String cbname;
	private String pass;
	private char[] password;
	private String sql2 = "SELECT * FROM employees_table";
	private Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	private JFrame frame;
	private JPasswordField pwf;
	private JComboBox<String> combox;
	private String getpassword, getname, grade;
	private String sql = "SELECT employee_name ,employee_id || '_' || employee_name, employee_pw FROM employees_table WHERE employee_name = ?";
	private String name;
	
	public PasswordTextFieldListener(JComboBox<String> combox, String cbname, JPasswordField pwf, JFrame frame) {
		this.cbname = cbname;
		this.pwf = pwf;
		this.combox = combox;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		cbname = combox.getSelectedItem().toString();
		String result = cbname.substring(cbname.length() - 3, cbname.length());

		password = pwf.getPassword();
		pass = new String(password); // 입력한 값
		grade = new GetEmployeeGrade(result).getGrade();
		try (Connection conn = HikariCP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, result);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				getname = rs.getString(1);
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

		if (pass.equals(getpassword) && result.equals(getname)) {
//			System.out.println("로그인 성공");
//			System.out.println("얻어온 비밀번호 = " + pass);
			LoginEmployeeInfoLabel.getLabel().setText(cbname);
			frame.dispose();
			new ChoosePageFrame(grade, result);
		} else {
			UIManager.put("OptionPane.messageFont",new Font("맑은 고딕",Font.PLAIN,12));
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다", "SYSTEM", JOptionPane.CANCEL_OPTION);
		}

	}

}
