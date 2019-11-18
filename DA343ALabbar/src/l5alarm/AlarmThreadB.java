package l5alarm;

import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AlarmThreadB implements AlarmListener {
	private Thread thread;
	private long ms;
//	private AlarmListener al;
	private LinkedList<AlarmListener> list = new LinkedList<AlarmListener>();
	
	public AlarmThreadB(long ms) {
		this.ms = ms;
	}
	
	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}
	
	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {
				
			}
			for(AlarmListener al : list) {
				al.alarm();
			}
			thread = null;
		}
	}

	public void alarm() {
		// TODO Auto-generated method stub
		
	}
	
	public void addAlarmListener(AlarmListener listener) {
		list.add(listener);
		
	}
}

