package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class Login_combobox_listener implements ActionListener {
	JComboBox combox;
	
	public Login_combobox_listener(JComboBox combox) {
		this.combox = combox;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cbname = combox.getSelectedItem().toString();
		System.out.println(cbname);
		
	}

}
