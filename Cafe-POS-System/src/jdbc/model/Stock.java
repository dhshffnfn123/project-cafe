package jdbc.model;

public class Stock {
	
	private int Stock_id;
	private String Stock_name;
	private int Stock_count;
	
	public int getStock_id() {
		return Stock_id;
	}
	
	public void setStock_id(int stock_id) {
		Stock_id = stock_id;
	}
	
	public String getStock_name() {
		return Stock_name;
	}
	
	public void setStock_name(String stock_name) {
		Stock_name = stock_name;
	}
	
	public int getStock_count() {
		return Stock_count;
	}
	
	public void setStock_count(int stock_count) {
		Stock_count = stock_count;
	}
	
}
