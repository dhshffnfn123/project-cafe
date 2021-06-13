package jdbc.model;

public class Drink {
	
	private int drink_id;
	private int m_type_id;
	private String drink_name;
	private int drink_price;
	
	public int getDrink_id() {
		return drink_id;
	}
	
	public void setDrink_id(int drink_id) {
		this.drink_id = drink_id;
	}
	
	public int getM_type_id() {
		return m_type_id;
	}
	
	public void setM_type_id(int m_type_id) {
		this.m_type_id = m_type_id;
	}
	
	public String getDrink_name() {
		return drink_name;
	}
	
	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}
	
	public int getDrink_price() {
		return drink_price;
	}
	
	public void setDrink_price(int drink_price) {
		this.drink_price = drink_price;
	}
	
}
