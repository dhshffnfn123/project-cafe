package swing.method;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;

public class CurrentTimeClock implements Runnable {
	private Thread thread;
	private JLabel label;
	
	public CurrentTimeClock() {
		label = new JLabel();
		
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public JLabel setClock() {
		return label;
	}

	@Override
	public void run() {
		while (true) {
			Calendar now = Calendar.getInstance();
			SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd (E)");
			SimpleDateFormat time = new SimpleDateFormat("a HH : mm");
			// &nbsp; : html에서 공백
			String clock_format = "<HTML>" + date.format(Calendar.getInstance().getTime()) + "<br>&nbsp;" + 
					time.format(Calendar.getInstance().getTime()) + "</HTML>";
			
			label.setText(clock_format);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
