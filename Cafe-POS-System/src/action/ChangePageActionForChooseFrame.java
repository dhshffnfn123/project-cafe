package action;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import jdbc.hikari.HikariCP;
import swing.frame.CheckSalesFrame;
import swing.frame.ChoosePageFrame;
import swing.frame.EmployeesManagementFrame;
import swing.frame.OrderFrame;
import swing.frame.StockManagementFrame;

public class ChangePageActionForChooseFrame implements ActionListener {
	
	private JFrame frame;
	private String grade, order_name;
	
	public ChangePageActionForChooseFrame(JFrame frame, String grade, String order_name) {
		this.order_name = order_name;
		this.frame = frame;
		this.grade = grade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String btnName = ((JButton)e.getSource()).getText();
		switch (btnName) {
		case "직원 관리":
			if (grade.equals("MANAGER")) {
				frame.dispose();
				new EmployeesManagementFrame(grade, order_name);	
			} else {
				UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.PLAIN, 12));
				JOptionPane.showMessageDialog(null, "접근 권한이 없습니다.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "판매 등록":
			frame.dispose();
			new OrderFrame(grade, order_name);
			break;
		case "매출 조회":
			frame.dispose();
			new CheckSalesFrame(grade, order_name);
			break;
		case "재고 관리":
			frame.dispose();
			new StockManagementFrame(grade, order_name);
			break;
		}
	}
	
}
