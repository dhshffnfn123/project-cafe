package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swing.frame.AccumulatedFundFrame;

public class PaymentButtonAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new AccumulatedFundFrame();
	}

}
