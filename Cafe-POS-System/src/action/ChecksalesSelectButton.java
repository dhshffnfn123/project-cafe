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

public class ChecksalesSelectButton implements ActionListener {
	
	private JFrame jf;
	private JTable jt;
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
	
	private String sql4 = "SELET COUNT(*), SUM(order) FROM ";
	private DefaultTableModel model;
	private Object[][] data = new String[0][0];
	private String date;
	private int result = 0;
	private int count = 0;
	private TotalInfo total;
	
	public ChecksalesSelectButton(JFrame jf, JButton button, JComboBox<String> box, DefaultTableModel model, JTable jt) {
		this.jf = jf;
		this.button = button;
		this.box = box;
		this.model = model;
		this.jt = jt;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		model.setNumRows(0);

			try (
					Connection conn = HikariCP.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					PreparedStatement pstmt3 = conn.prepareStatement(sql3);
					ResultSet rs = pstmt.executeQuery();
					ResultSet rs2 = pstmt2.executeQuery();
					ResultSet rs3 = pstmt3.executeQuery();
			) {
				if(box.getSelectedItem().toString().equals("연 매출")) {
					while (rs.next()) {
						date = rs.getString(1);
						count = rs.getInt(2);
						result = rs.getInt(3);
						Object[] row = {date,count,result};
						model.addRow(row);
						
					}
				} else if(box.getSelectedItem().toString().equals("월 매출")){
					while (rs2.next()) {
						date = rs2.getString(1);
						count = rs2.getInt(2);
						result = rs2.getInt(3);
						Object[] row = {date,count,result};
						model.addRow(row);
					}
				} else if(box.getSelectedItem().toString().equals("일 매출")){
					while (rs3.next()) {
						date = rs3.getString(1);
						count = rs3.getInt(2);
						result = rs3.getInt(3);
						Object[] row = {date,count,result};
						model.addRow(row);
					}
				}
					}catch (SQLException e1) {
				e1.printStackTrace();
			}	
			
	}
}		



