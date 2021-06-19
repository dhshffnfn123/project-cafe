package swing.method;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.*;

public class AccumulatedRoundedButton extends JButton {
	private String grade, order_name;
	public AccumulatedRoundedButton(String grade, String order_name) {
		super();
		this.order_name = order_name;
		this.grade = grade;
		decorate();
	}

	public AccumulatedRoundedButton(String text, String grade, String order_name) {
		super(text);
		this.grade = grade;
		this.order_name = order_name;
		decorate();
	}

	public AccumulatedRoundedButton(Action action) {
		super(action);
		decorate();
	}

	public AccumulatedRoundedButton(Icon icon) {
		super(icon);
		decorate();
	}

	public AccumulatedRoundedButton(String text, Icon icon) {
		super(text, icon);
		decorate();
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		Color c = new Color(0,66,56); // 배경색 결정
		Color o = Color.white; // 글자색 결정

		int width = getWidth();
		int height = getHeight();
		
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(c.darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(c.brighter());
		} else {
			graphics.setColor(c);
		}
		graphics.fillRoundRect(0, 0, width, height, 20, 20); // 둥글기 조정
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(o);
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}