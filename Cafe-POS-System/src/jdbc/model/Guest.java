package jdbc.model;

public class Guest {
	
	private int guest_id;
	private String guest_name;
	private int guest_point;
	private int coupon;
	
	public int getGuest_id() {
		return guest_id;
	}
	
	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}
	
	public String getGuest_name() {
		return guest_name;
	}
	
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
	
	public int getGuest_point() {
		return guest_point;
	}
	
	public void setGuest_point(int guest_point) {
		this.guest_point = guest_point;
	}
	
	public int getCoupon() {
		return coupon;
	}
	
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	
}
