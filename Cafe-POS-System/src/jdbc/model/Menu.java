package jdbc.model;

public class Menu {
	
	private int menu_id;
	private Drink dink;
	private Product product;
	private Rtd rtd;
	
	public int getMenu_id() {
		return menu_id;
	}
	
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	
	public Drink getDink() {
		return dink;
	}
	
	public void setDink(Drink dink) {
		this.dink = dink;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Rtd getRtd() {
		return rtd;
	}
	
	public void setRtd(Rtd rtd) {
		this.rtd = rtd;
	}
	
}
