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
	private ArrayList<String> drinks = new ArrayList<>();
	
	private void getSetValue(String drink_name, int drink_price) {
		setDrink_name(drink_name);
		setDrink_price(drink_price);
		getDrink_name();
		getDrink_price();
	}
	
	public ArrayList<String> getDrinks() {
		return drinks;
	}
	
	public DrinkView(int type_id) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setInt(1, type_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				getSetValue(rs.getString(3), rs.getInt(4));
				// HTML, CSS문법 사용해서 버튼 텍스트 중앙 정렬
				String drink = String.format("<HTML><body style='text-align:center;'>%s<br>%d</body></HTML>", getDrink_name(), getDrink_price());
				drinks.add(drink);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// ArrayList로 사용
		ArrayList<String> test = new DrinkView(10).getDrinks();
	}
	
}
