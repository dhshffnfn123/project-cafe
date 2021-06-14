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

public class ChecksalesSelectButton implements ActionListener {
	
	private JFrame jf;
	private JButton button;
	private JComboBox<String> box;
	private JScrollPane jsp;
	private String sql = "SELECT TO_CHAR(order_time, 'yy'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, 'yy') "
			+ "ORDER BY TO_CHAR(order_time, 'yy')";
	
	private String sql2 = "SELECT TO_CHAR(order_time, 'yy-mm'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, 'yy-mm') "
			+ "ORDER BY TO_CHAR(order_time, 'yy-mm')";
	
	private String sql3 = "SELECT TO_CHAR(order_time, 'yy-mm-dd'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table "
			+ "GROUP BY TO_CHAR(order_time, 'yy-mm-dd') "
			+ "ORDER BY TO_CHAR(order_time, 'yy-mm-dd')";
	
	private DefaultTableModel model;
	private String date;
	private int result = 0;
	private int count = 0;
	
	public ChecksalesSelectButton(JFrame jf, JButton button, JComboBox<String> box, DefaultTableModel model) {
		this.jf = jf;
		this.button = button;
		this.box = box;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(box.getSelectedItem().toString().equals("연 매출")) {
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
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(box.getSelectedItem().toString().equals("월 매출")){
			try (
					Connection conn = HikariCP.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					ResultSet rs = pstmt.executeQuery();
			) {
				while (rs.next()) {
					date = rs.getString(1);
					count = rs.getInt(2);
					result = rs.getInt(3);
					Object[] row = {date,count,result};
					model.addRow(row);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(box.getSelectedItem().toString().equals("일 매출")){
			try (
					Connection conn = HikariCP.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql3);
					ResultSet rs = pstmt.executeQuery();
			) {
				while (rs.next()) {
					date = rs.getString(1);
					count = rs.getInt(2);
					result = rs.getInt(3);
					Object[] row = {date,count,result};
					model.addRow(row);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}



