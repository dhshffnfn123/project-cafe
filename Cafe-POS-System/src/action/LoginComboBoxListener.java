package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class LoginComboBoxListener implements ActionListener {
	
	private JComboBox<String> combox;
	String cbname;
	
	public LoginComboBoxListener(JComboBox<String> combox) {
		this.combox = combox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cbname = combox.getSelectedItem().toString();
	}

}
