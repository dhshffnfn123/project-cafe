package jdbc.model;

public class Point {
	
	private int point_id;
	private Guest guest_id;
	private Cash cash_id;
	
	public int getPoint_id() {
		return point_id;
	}
	
	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}
	
	public Guest getGuest_id() {
		return guest_id;
	}
	
	public void setGuest_id(Guest guest_id) {
		this.guest_id = guest_id;
	}
	
	public Cash getCash_id() {
		return cash_id;
	}
	
	public void setCash_id(Cash cash_id) {
		this.cash_id = cash_id;
	}

}
