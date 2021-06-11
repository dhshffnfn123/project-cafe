package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;
import jdbc.model.Drink;

public class DrinkView extends Drink {

	public DrinkView(String drink_name, int drink_price) {
		setDrink_name(drink_name);
		setDrink_price(getDrink_price());
		getDrink_name();
		getDrink_price();
	}
	
	public static void drinkSearchView(String group_name) {
		
		String search_name = group_name;
		String sql = "SELECT drink_name, drink_price FROM drink_table "
				+ "INNER JOIN menu_type USING (m_type_id) WHERE m_name LIKE ?";
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(null);
		) {
			
			pstmt.setString(1, search_name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(new DrinkView(rs.getString(1), rs.getInt(2)));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s\n%s", super.getDrink_name(), super.getDrink_price());
	}
	
}
