package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc.hikari.HikariCP;

public class UpdateConfirmBtn implements ActionListener {
	
	private JFrame frame;
	private JTextField tf, count;
	private String nameVal, countVal, name;
	private int countint;
	private JTable table;
	
	private DefaultTableCellRenderer dtcr_center;
	Font bigger_font = new Font("맑은 고딕", Font.BOLD, 50);
	Font big_font = new Font("맑은 고딕", Font.BOLD, 30);
	Font nomal_font = new Font("맑은 고딕", Font.BOLD, 20);
	Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	private String sql = "UPDATE stock_table SET stock_count = ? WHERE stock_name = ?";

	public UpdateConfirmBtn(JTextField count, JTable table, JFrame frame, String name) {
		this.frame = frame;
		this.table = table;
		this.count = count;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try (Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
				countVal = count.getText();
				
				countint = Integer.parseInt(countVal);
				System.out.println(countint);
				System.out.println(name);
				
				pstmt.setInt(1, countint);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		DefaultTableModel originmodel = (DefaultTableModel)table.getModel();
		
		DefaultTableModel updatemodel = (DefaultTableModel)(new StockTableAddData().getStockTable().getModel());
		
		originmodel.setRowCount(0);
		
		table.setModel(updatemodel);
		
		table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
		table.getTableHeader().setBackground(Color.pink);// 컬럼의 색상을 설정
		table.getTableHeader().setFont(small_font);
		table.getTableHeader().setForeground(Color.black);

		String[] header = new StockTableAddData().give_header();

		table.getColumn(header[0]).setPreferredWidth(100); // 컬럼당 넓이 설정인데 모든 컬럼을 테이블의 넓이에 '얼추' 맞게 설정해야함
		table.getColumn(header[1]).setPreferredWidth(900);
		table.getColumn(header[2]).setPreferredWidth(160);
		table.setFont(nomal_font);
		
		
		dtcr_center = new DefaultTableCellRenderer();

		dtcr_center.setHorizontalAlignment(SwingConstants.CENTER); // dtcr_center의 위치를 center로 지정
		
		TableColumnModel ts = table.getColumnModel(); // 정렬할 테이블의 columnModel을 가져옴
		ts.getColumn(0).setCellRenderer(dtcr_center);// product_id 컬럼을 센터 정렬
		ts.getColumn(1).setCellRenderer(dtcr_center);
		ts.getColumn(2).setCellRenderer(dtcr_center);

		updatemodel.fireTableDataChanged();
		
		UIManager.put("OptionPane.messageFont", nomal_font);
		JOptionPane.showMessageDialog(null, "재고수량이 수정되었습니다", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	
		

	}

}



