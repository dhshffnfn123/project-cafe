package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.hikari.HikariCP;
import jdbc.model.Rtd;

public class RtdView extends Rtd {
	
	private String sql = "SELECT * FROM rtd_table WHERE m_type_id = 210";
	private ArrayList<String> rtds = new ArrayList<>();
	
	private void getRtd(String rtd_name, int rtd_price) {
		setRtd_name(rtd_name);
		setRtd_price(rtd_price);
		getRtd_name();
		getRtd_price();
	}
	
	public ArrayList<String> getRtds() {
		return rtds;
	}
	
	public RtdView() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				getRtd(rs.getString(3), rs.getInt(4));
				String rtd = String.format("<HTML><body style='text-align:center;'>%s<br>%d</body></HTML>", getRtd_name(), getRtd_price());
				rtds.add(rtd);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		ArrayList<String> test = new RtdView().getRtds();
		System.out.println(test.toString());
	}

}
