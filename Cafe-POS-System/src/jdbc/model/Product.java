package jdbc.model;

public class Product {
	
	private int product_id;
	private int m_type_id;
	private String product_name;
	private int product_price;
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getM_type_id() {
		return m_type_id;
	}
	
	public void setM_type_id(int m_type_id) {
		this.m_type_id = m_type_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public int getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
}
