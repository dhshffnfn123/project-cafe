package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import swing.frame.CheckSalesFrame;
import swing.frame.ChoosePageFrame;
import swing.frame.EmployeesManagementFrame;
import swing.frame.OrderFrame;
import swing.frame.StockManagementFrame;

public class ChangePageActionForChooseFrame implements ActionListener {
	
	private JFrame frame;
	
	public ChangePageActionForChooseFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnName = ((JButton)e.getSource()).getText();
		frame.dispose();
		
		switch (btnName) {
		case "직원 관리":
			new EmployeesManagementFrame();
			break;
		case "판매 등록":
			new OrderFrame();
			break;
		case "매출 조회":
			new CheckSalesFrame();
			break;
		case "재고 관리":
			new StockManagementFrame();
			break;
		}
	}
	
}
