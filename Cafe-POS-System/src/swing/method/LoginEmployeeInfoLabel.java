package swing.method;

import javax.swing.JLabel;

public class LoginEmployeeInfoLabel {
	
	private String cbname;
	private static JLabel label;
	
	static {
		label = new JLabel();
	}
	
	public String getCbname() {
		return cbname;
	}

	public void setCbname(String cbname) {
		this.cbname = cbname;
	}
	
	public static JLabel getLabel() {
		return label;
	}

}
