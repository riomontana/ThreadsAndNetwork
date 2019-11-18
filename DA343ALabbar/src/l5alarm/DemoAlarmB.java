package l5alarm;

public class DemoAlarmB {
	public DemoAlarmB(int ms) {
		AlarmThreadB bt = new AlarmThreadB(ms);
		bt.startAlarm();
		bt.addAlarmListener(new AlarmPrinter());
		bt.addAlarmListener(new WakeUpPrinter());
	}
	
	private class AlarmPrinter implements AlarmListener {
		public void alarm() {
			 System.out.println("ALARM");
		}
	}
	
	private class WakeUpPrinter implements AlarmListener {

		@Override
		public void alarm() {
		System.out.println("Wake Up beatch!");
			
		}
		
	}
	
	public static void main(String[] args) {
		DemoAlarmB da = new DemoAlarmB(4000);
	}
}
