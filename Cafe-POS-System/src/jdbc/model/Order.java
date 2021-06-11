package jdbc.model;

import java.util.Date;

public class Order {
	
	private int Order_id;
	private int Guest_id;
	private int Stock_id;
	private int Menu_id;
	private int Cash_id;
	private Date Order_time;
	private int Employee_id;
	
	public int getOrder_id() {
		return Order_id;
	}

	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}

	public int getGuest_id() {
		return Guest_id;
	}

	public void setGuest_id(int guest_id) {
		Guest_id = guest_id;
	}

	public int getStock_id() {
		return Stock_id;
	}

	public void setStock_id(int stock_id) {
		Stock_id = stock_id;
	}

	public int getMenu_id() {
		return Menu_id;
	}

	public void setMenu_id(int menu_id) {
		Menu_id = menu_id;
	}

	public int getCash_id() {
		return Cash_id;
	}

	public void setCash_id(int cash_id) {
		Cash_id = cash_id;
	}

	public Date getOrder_time() {
		return Order_time;
	}

	public void setOrder_time(Date order_time) {
		Order_time = order_time;
	}

	public int getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(int employee_id) {
		Employee_id = employee_id;
	}

}
