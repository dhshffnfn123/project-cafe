package swing.method;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//Àû¸³È­¸é ÆË¾÷Ã¢ 
public class AccrualCompletionJOP extends JOptionPane{
	
	public AccrualCompletionJOP() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		UIManager.put("OptionPane.messageFont",new Font("¸¼Àº °íµñ",Font.PLAIN,12));
		
	}

}







