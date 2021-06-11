package jdbc.model;

import java.util.Date;

public class Cash {
	
	private int cash_id;
	private String payment_method;
	private Date payment_date;
	private boolean payment_state;
	
	public int getCash_id() {
		return cash_id;
	}
	
	public void setCash_id(int cash_id) {
		this.cash_id = cash_id;
	}
	
	public String getPayment_method() {
		return payment_method;
	}
	
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	
	public Date getPayment_date() {
		return payment_date;
	}
	
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	
	public boolean isPayment_state() {
		return payment_state;
	}
	
	public void setPayment_state(boolean payment_state) {
		this.payment_state = payment_state;
	}
	
}
