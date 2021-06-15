package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableModel;

public class StockGetTableVal implements MouseListener {
	private JTable table;
	private Object[] data;
	private int id, count;
	private String name;
	
	public StockGetTableVal(JTable table) {
		this.table = table;
	}
	
	public int GetStockIdData() {
		return id;
	}
	public String GetStockNameData() {
		return name;
	}
	public int GetStockCountData() {
		return count;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow(); // 선택한 셀의 행 번호 계산
		TableModel model = table.getModel();
		
		int id = (int)model.getValueAt(row, 0);
		String name = (String)model.getValueAt(row, 1);
		int count = (int)model.getValueAt(row, 2);
		
		System.out.println("ID : " + id);
		System.out.println("NAME : " + name);
		System.out.println("COUNT : " + count);
		
//		GetStockIdData();
//		GetStockNameData();
//		GetStockCountData();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
