package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.hikari.HikariCP;
import jdbc.model.Drink;

public class DrinkView extends Drink {

	private String sql = "SELECT * FROM drink_table WHERE m_type_id = ?";
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<Integer> price = new ArrayList<>();
	
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<Integer> getPrice() {
		return price;
	}
	
	public DrinkView(int type_id) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setInt(1, type_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				name.add(rs.getString(3));
				price.add(rs.getInt(4));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
