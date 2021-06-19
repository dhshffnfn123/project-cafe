package swing.method;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

public class ReceiptTextArea extends TextArea {
	
	public ReceiptTextArea(String html) {
		setText(html);
		setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		setLocation(0, 0);
		setSize(660, 950);
		setBackground(Color.WHITE);
		new ReceiptJScrollPane().add(this);
	}
}