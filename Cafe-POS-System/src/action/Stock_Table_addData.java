package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;

public class Stock_Table_addData  {
	private String colNames[] = {"아이디", "품 명", "재고 수량"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private String select_sql;
	
	public defaultTableModel getStockTable() {
		return model;
	}
	
	
	private void Select_addData() {
		select_sql = 
				"select stock_id, stock_name, stock_count from stock_table";
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(select_sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				model.addRow(new Object[] {rs.getString("STOCK_ID"), rs.getString("STOCK_NAME"),
						rs.getString("STOCK_COUNT")
				});
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
	}

	
}
				
