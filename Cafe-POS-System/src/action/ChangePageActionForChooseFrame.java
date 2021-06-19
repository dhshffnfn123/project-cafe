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
	private String grade;
	public ChangePageActionForChooseFrame(JFrame frame, String grade) {
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
				new EmployeesManagementFrame(grade);	
			} else {
				UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.BOLD, 45));
				JOptionPane.showMessageDialog(null, "님목두 지시입니다. 돌아가세요", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "판매 등록":
			frame.dispose();
			new OrderFrame(grade);
			break;
		case "매출 조회":
			frame.dispose();
			new CheckSalesFrame(grade);
			break;
		case "재고 관리":
			frame.dispose();
			new StockManagementFrame(grade);
			break;
		}
	}
	
}
