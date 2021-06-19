package jdbc.model;

import javax.swing.JButton;

public class OptionButton extends JButton {
	
	private String name;
	private Integer price;
	
	public OptionButton(String name, Integer price) {
		super("<HTML><body style='text-align:center;'>" + name + "<br>+" + price + "</HTML>");
		this.setName(name);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
