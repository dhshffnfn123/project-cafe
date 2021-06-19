package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableModel;

public class StockGetTableVal implements MouseListener {

	private JTable table;
	private String id;
	private int count;
	private String name;

	public StockGetTableVal(JTable table) {
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int row = table.getSelectedRow(); // 선택한 셀의 행 번호 계산
		TableModel model = table.getModel();

		id = (String)model.getValueAt(row, 0);
		name = (String) model.getValueAt(row, 1);
		count = (int) model.getValueAt(row, 2);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
