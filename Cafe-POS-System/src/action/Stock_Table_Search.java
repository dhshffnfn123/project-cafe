package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import jdbc.hikari.HikariCP;

public class Stock_Table_Search implements ActionListener {
	private String sql = "SELECT * FROM stock_table WHERE";
	private String search;
	private JTextField textF;
	
	public Stock_Table_Search(JTextField textF, String search) {
		this.textF = textF;
		this.search = search;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
		
	}

}
