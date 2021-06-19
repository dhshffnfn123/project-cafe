package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swing.frame.OrderFrame;
import swing.method.ReceiptLabel;

// 결제완료시 영수증출력 후 테이블 정보 삭제
public class PaymentFinishButton implements ActionListener {
	private String order_name;
	DefaultTableModel model;
	JTable table;

	public PaymentFinishButton(JTable table, String order_name) {
		this.order_name = order_name;
		this.table = table;
		this.model = (DefaultTableModel) table.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ReceiptLabel(table, order_name);
		model.setRowCount(0);
		
		OrderFrame.GettableInfo().removeAll(OrderFrame.GettableInfo());
//		tableInfo.removeAll(tableInfo);
		OrderFrame.GetMenuHash().clear();
		OrderFrame.getTotalmoney().setText(String.valueOf(0));
	}
}
