package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;
import jdbc.model.Rtd;

public class RtdView extends Rtd {
	
	public RtdView(String rtd_name, int rtd_price) {
		setRtd_name(rtd_name);
		setRtd_price(rtd_price);
		getRtd_name();
		getRtd_price();
	}
	
	public static void RtdSearchView(String group_name) {
		
		String search_name = group_name;
		String sql = "SELECT rtd_name, rtd_price FROM rtd_table "
				+ "INNER JOIN menu_type USING (m_type_id) WHERE m_name LIKE ?";
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setString(1, search_name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(new RtdView(rs.getString(1), rs.getInt(2)));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
