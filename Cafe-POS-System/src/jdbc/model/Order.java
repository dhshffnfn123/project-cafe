package jdbc.model;

import java.util.Date;

public class Order {
	
	private int order_id;
	private int stock_id;
	private int m_type_id;
	private Date order_time;
	private int employee_id;
	private int order_total;
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public int getStock_id() {
		return stock_id;
	}
	
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	
	public int getM_type_id() {
		return m_type_id;
	}
	
	public void setM_type_id(int m_type_id) {
		this.m_type_id = m_type_id;
	}
	
	public Date getOrder_time() {
		return order_time;
	}
	
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	
	public int getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public int getOrder_total() {
		return order_total;
	}
	
	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}

}
