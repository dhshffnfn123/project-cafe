package jdbc.model;

public class Option {

	private int option_id;
	private int m_type_id;
	private String option_name;
	private int option_price;
	
	public int getOption_id() {
		return option_id;
	}
	
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	
	public int getM_type_id() {
		return m_type_id;
	}
	
	public void setM_type_id(int m_type_id) {
		this.m_type_id = m_type_id;
	}
	
	public String getOption_name() {
		return option_name;
	}
	
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	
	public int getOption_price() {
		return option_price;
	}
	
	public void setOption_price(int option_price) {
		this.option_price = option_price;
	}
	
}
