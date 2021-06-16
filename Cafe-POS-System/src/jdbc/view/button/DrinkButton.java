package jdbc.view.button;

import javax.swing.JButton;

public class DrinkButton extends JButton {

	private String menuName;
	private String price;
	
	public DrinkButton(String menuName, String price) {
		super("<HTML>" + menuName +"<br>" + price + "¿ø</HTML>");
		this.setMenuName(menuName);
		this.setPrice(price);
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
