package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;
import jdbc.method.TotalInfo;

public class CheckSalesSelectButton implements ActionListener {

	private JFrame check_frame;
	private JTable select_table, total_table;
	private JButton button;
	private JComboBox<String> box;
	private JScrollPane table_scroll;
	private String sql = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total)" + "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, 'YYYY')" + "ORDER BY TO_CHAR(order_time, 'YYYY')";

	private String sql2 = "SELECT TO_CHAR(order_time, 'YYYY-MM'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY-MM')" + "ORDER BY TO_CHAR(order_time, 'YYYY-MM')";

	private String sql3 = "SELECT TO_CHAR(order_time, 'YYYY-MM-DD'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY-MM-DD')"
			+ "ORDER BY TO_CHAR(order_time, 'YYYY-MM-DD')";

	private String sql4 = "select count(*), to_char(sum(order_total), '999,999,999L') from order_table";

	private DefaultTableModel model, total_model;
	private Object[][] data = new String[0][0];
	private String date, un,total_result;
	private int result = 0;
	private int count = 0, total_count = 0;
	private TotalInfo total;

	public CheckSalesSelectButton(JFrame check_frame, JButton button, JComboBox<String> box, DefaultTableModel model,
			DefaultTableModel total_model, JTable select_table, JTable total_table) {
		this.check_frame = check_frame;
		this.button = button;
		this.box = box;
		this.model = model;
		this.total_model = total_model;
		this.select_table = select_table;
		this.total_table = total_table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		model.setNumRows(0);
		total_model.setNumRows(0);
		try (Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				PreparedStatement pstmt4 = conn.prepareStatement(sql4);

				ResultSet rs = pstmt.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				ResultSet rs3 = pstmt3.executeQuery();
				ResultSet rs4 = pstmt4.executeQuery();

		) {
			if (box.getSelectedItem().toString().equals("연 매출")) {
				while (rs.next()) {
					date = rs.getString(1);
					count = rs.getInt(2);
					result = rs.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs4.next()) {
					total_count = rs4.getInt(1);
					total_result = rs4.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			} else if (box.getSelectedItem().toString().equals("월 매출")) {
				while (rs2.next()) {
					date = rs2.getString(1);
					count = rs2.getInt(2);
					result = rs2.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs4.next()) {
					total_count = rs4.getInt(1);
					total_result = rs4.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			} else if (box.getSelectedItem().toString().equals("일 매출")) {
				while (rs3.next()) {
					date = rs3.getString(1);
					count = rs3.getInt(2);
					result = rs3.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs4.next()) {
					total_count = rs4.getInt(1);
					total_result = rs4.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
