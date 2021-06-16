package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;

public class TotalInfo {
	
	private String sql = "SELECT TO_CHAR(order_time, 'yy'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, 'yy') "
			+ "ORDER BY TO_CHAR(order_time, 'yy')";
	/*
	 * "SELECT TO_CHAR(order_time, ?), COUNT(order_total), SUM(order_total) "
			+ "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, ?) "
			+ "ORDER BY TO_CHAR(order_time, ?)"
	 */
	private DefaultTableModel model, total_model;
	private JTable table; 
	private	String[] header = {"날짜", "판매건수", "판매금액"};
	private	String[] total_header = {"전체 판매건수", "총 매출액"};	
	private Object[][] data = new String[0][0];
	private String date;
	private int result = 0;
	private int count = 0;
	
	public DefaultTableModel getTotalInfo() {
		in_Order();
		return model;
	}
	public DefaultTableModel getTotalInfoTotal() {
		in_Order_total();
		return total_model;
	}
	
	
	private void in_Order() {
		model = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
	}
	
	private void in_Order_total() {
		total_model = new DefaultTableModel(data, total_header) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
	}
	    
	private void select_Order() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				date = rs.getString(1);
				count = rs.getInt(2);
				result = rs.getInt(3);
				Object[] row = {date,count,result};
				model.addRow(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
