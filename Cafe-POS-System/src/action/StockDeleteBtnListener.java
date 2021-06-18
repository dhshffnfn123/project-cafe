package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import jdbc.hikari.HikariCP;
import swing.frame.StockUpdateFrame;

public class StockDeleteBtnListener implements ActionListener {

	private JTable table;
	private String name;
	String id;
	private int count;

	private DefaultTableCellRenderer dtcr_center;
	private Font nomal_font = new Font("맑은 고딕", Font.PLAIN, 20);
	private Font system_font = new Font("맑은 고딕", Font.BOLD, 20);
	private Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	private String sql = "DELETE FROM stock_table WHERE stock_id = ?";

	public StockDeleteBtnListener(JTable table) {
		this.table = table;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow(); // 선택한 셀의 행 번호 계산
		TableModel model = table.getModel();

		if (row == -1) {
			UIManager.put("OptionPane.messageFont", nomal_font);
			JOptionPane.showMessageDialog(null, "정보 없음", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
		} else {
			id = (String) model.getValueAt(row, 0);

			try (Connection conn = HikariCP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			DefaultTableModel originmodel = (DefaultTableModel) table.getModel();

			DefaultTableModel updatemodel = (DefaultTableModel) (new StockTableAddData().getStockTable().getModel());

			originmodel.setRowCount(0);

			table.setModel(updatemodel);

			table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
			table.getTableHeader().setBackground(new Color(163, 148, 132));// 컬럼의 색상을 설정
			table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 30));
			table.getTableHeader().setForeground(Color.white);

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

			UIManager.put("OptionPane.messageFont", system_font);
			JOptionPane.showMessageDialog(null, "재고품목이 삭제되었습니다", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
