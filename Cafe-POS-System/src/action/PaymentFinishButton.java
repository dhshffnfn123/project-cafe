package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdbc.method.DeletereceiptInfo;
import jdbc.method.DropSequenceReceiptInfo;
import swing.view.ReceiptView;

// 결제완료시 영수증출력 후 테이블 정보 삭제, 시퀀스 삭제, 시퀀스 생성
public class PaymentFinishButton implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new ReceiptView();		
		new DeletereceiptInfo();
	}
}