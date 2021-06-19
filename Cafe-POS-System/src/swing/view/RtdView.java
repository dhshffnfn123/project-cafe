package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.hikari.HikariCP;
import jdbc.model.Rtd;

public class RtdView extends Rtd {
	// RTD는 타입이 한개라서 타입id 안받아도 됨
	private String sql = "SELECT * FROM rtd_table WHERE m_type_id = 210";
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<Integer> price = new ArrayList<>();
	
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<Integer> getPrice() {
		return price;
	}
	
	public RtdView() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				name.add(rs.getNString(3));
				price.add(rs.getInt(4));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	

}
