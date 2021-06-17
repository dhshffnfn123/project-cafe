package jdbc.model;

public class MenuButtonData {
	 int orderNum;
	 String menuName;
	 int qty;
	 int menuPrice;
	
	 public MenuButtonData( int num ,String name , int qty , int price) {
		this.orderNum = num;
		this.menuName = name;
		this.qty = qty;
		this.menuPrice = price;
	}
	 

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String[] getTableRow() {
		String[] tableRow = new String[4];
		tableRow[0] = String.valueOf(orderNum);
		tableRow[1] = menuName;
		tableRow[2] = String.valueOf(qty);
		tableRow[3] = String.valueOf(menuPrice);
		
		return tableRow;
	}

}