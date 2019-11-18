package l5alarm;

import java.util.*;

public class DemoAlarmA {
	public DemoAlarmA(int ms) {
		AlarmThreadA at = new AlarmThreadA(ms);
		at.addObserver(new AlarmPrinter());
		at.addObserver(new WakeUpPrinter());
		at.addObserver(new ConsolePrinter("console printer"));
		at.startAlarm();
	}
	
	private class AlarmPrinter implements Observer {
		public void update(Observable o, Object arg) {
			System.out.println("ALARRRRMMMMM!!!!!");
		}
	}
	
	private class WakeUpPrinter implements Observer {
		public void update(Observable o, Object arg) {
			 System.out.println("Wake up!!");	
		}
	}
	
	private class ConsolePrinter implements Observer {
		private String string;
		
		public ConsolePrinter(String string) {
			this.string = string;
		}
		
		public void update(Observable o, Object arg) {
			System.out.println(string);
		}
	}
	
	public static void main(String[] args) {
		DemoAlarmA da = new DemoAlarmA(4000);
	}
}
