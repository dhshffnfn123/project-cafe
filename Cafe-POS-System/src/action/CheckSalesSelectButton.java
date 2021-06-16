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
	private JComboBox ybox;
	private JComboBox<String> mbox, dbox;
	private JScrollPane table_scroll;
	private String year;
	
	private String sql = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY')" + "ORDER BY TO_CHAR(order_time, 'YYYY')";

	private String sql2 = "SELECT TO_CHAR(order_time, 'YYYY-MM'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY-MM')" + "ORDER BY TO_CHAR(order_time, 'YYYY-MM')";

	private String sql3 = "SELECT TO_CHAR(order_time, 'YYYY-MM-DD'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY-MM-DD')"
			+ "ORDER BY TO_CHAR(order_time, 'YYYY-MM-DD')";

	
	
	private String sql_total = "select count(*), to_char(sum(order_total), '999,999,999L') from order_table";
	
	
	
	private String sql_year = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total) FROM order_table " +
	        "WHERE TO_CHAR(order_time, 'YYYY') LIKE ? " +
	        "GROUP BY TO_CHAR(order_time, 'YYYY')ORDER BY TO_CHAR(order_time, 'YYYY')";
	
	private String sql_month = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total) FROM order_table" +
	        "WHERE order_time like ?" +
	        "GROUP BY TO_CHAR(order_time, 'YYYY') ORDER BY TO_CHAR(order_time, 'YYYY')";
	
	private String sql_day = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total) FROM order_table" +
	        "WHERE order_time like ?" +
	        "GROUP BY TO_CHAR(order_time, 'YYYY') ORDER BY TO_CHAR(order_time, 'YYYY')";

	private DefaultTableModel model, total_model;
	private Object[][] data = new String[0][0];
	private String date, un,total_result;
	private int result = 0;
	private int count = 0, total_count = 0;
	private TotalInfo total;

	public CheckSalesSelectButton(JFrame check_frame, JButton button, DefaultTableModel model,
			DefaultTableModel total_model, JTable select_table, JTable total_table, 
			JComboBox ybox,JComboBox<String> mbox,JComboBox<String> dbox) {
		this.check_frame = check_frame;
		this.button = button;
		this.ybox = ybox;
		this.mbox = mbox;
		this.dbox = dbox;
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
				PreparedStatement pstmt = conn.prepareStatement(sql);	// 전체 연 매출
				PreparedStatement pstmt2 = conn.prepareStatement(sql2); // 전체 월 매출 
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);	// 전체 일 매출
				
				PreparedStatement pstmt_y = conn.prepareStatement(sql_year);
//				PreparedStatement pstmt_m = conn.prepareStatement(sql_month);
//				PreparedStatement pstmt_d = conn.prepareStatement(sql_day);
				
				PreparedStatement pstmt_total = conn.prepareStatement(sql_total); // 총 매출 계산

				ResultSet rs = pstmt.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				ResultSet rs3 = pstmt3.executeQuery();
				
//				ResultSet rs_m = pstmt_m.executeQuery();
//				ResultSet rs_d = pstmt_d.executeQuery();
				
				ResultSet rs_total = pstmt_total.executeQuery();

		) {
			
			year = ybox.getSelectedItem().toString();
			String month = mbox.getSelectedItem().toString();
			String day = dbox.getSelectedItem().toString();
			
			System.out.println(year);
			if(year.equals("년도 선택")) {
			System.out.println("select");
			}
			
			//// ALL 출력
			if (year.equals("ALL") 
					&& month.equals("None")
					&& day.equals("None")) {
				while (rs.next()) {
					date = rs.getString(1);
					count = rs.getInt(2);
					result = rs.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs_total.next()) {
					total_count = rs_total.getInt(1);
					total_result = rs_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			} else if (year.equals("ALL") 
					&& month.toString().equals("ALL")
					&& day.equals("None")) {
				while (rs2.next()) {
					date = rs2.getString(1);
					count = rs2.getInt(2);
					result = rs2.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs_total.next()) {
					total_count = rs_total.getInt(1);
					total_result = rs_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			} else if (year.equals("ALL") 
					&& month.equals("ALL")
					&& day.equals("ALL")) {
				while (rs3.next()) {
					date = rs3.getString(1);
					count = rs3.getInt(2);
					result = rs3.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				while (rs_total.next()) {
					total_count = rs_total.getInt(1);
					total_result = rs_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
			} else if (year.equals("년도 선택")){
				
				System.out.println("aaaa");
			} else {
				System.out.println("원하는 값");
				
				pstmt_y.setString(1, String.format("%%%s%%", year));
				
				pstmt_y.executeUpdate();
				ResultSet rs_y = pstmt_y.executeQuery();
				
				while (rs_y.next()) {
					date = rs_y.getString(1);
					count = rs_y.getInt(2);
					result = rs_y.getInt(3);
					
					Object[] row = { date, count, result };
					model.addRow(row);
					
				}
				rs_y.close();
				pstmt_y.close();
			}
			
			
			
	
			
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}

