package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swing.frame.AccumulatedFundFrame;

public class PaymentButtonAction implements ActionListener {
	
	private String grade, order_name;
	
	public PaymentButtonAction(String grade, String order_name) {
		this.grade = grade;
		this.order_name = order_name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new AccumulatedFundFrame(grade, order_name);
	}

}
