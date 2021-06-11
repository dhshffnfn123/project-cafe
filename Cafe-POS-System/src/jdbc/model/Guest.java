package jdbc.model;

public class Guest {
	
	private int Guest_id;
	private String Guest_name;
	private int Guest_number;
	private int Guest_point;
	private int Coupon;
	
	public int getGuest_id() {
		return Guest_id;
	}

	public void setGuest_id(int guest_id) {
		Guest_id = guest_id;
	}

	public String getGuest_name() {
		return Guest_name;
	}

	public void setGuest_name(String guest_name) {
		Guest_name = guest_name;
	}

	public int getGuest_number() {
		return Guest_number;
	}

	public void setGuest_number(int guest_number) {
		Guest_number = guest_number;
	}

	public int getGuest_point() {
		return Guest_point;
	}

	public void setGuest_point(int guest_point) {
		Guest_point = guest_point;
	}

	public int getCoupon() {
		return Coupon;
	}

	public void setCoupon(int coupon) {
		Coupon = coupon;
	}
	
}
