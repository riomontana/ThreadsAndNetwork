package f4;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class TwoIcon2 extends TwoIconLabel {
    private java.util.Timer timer;
    
    public TwoIcon2(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon2(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon2(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
    	ChangeIcon cc = new ChangeIcon();
        timer = new java.util.Timer();
        timer.schedule(new ToDo(),delay,delay);
    }
    
    private class ToDo extends TimerTask {
    	private ChangeIcon ci = new ChangeIcon();
    	
    	public void run() {
    		SwingUtilities.invokeLater(ci);
    	}
    }

    private class ChangeIcon implements Runnable {
        public void run() {
        	changeIcon();
        }
    }
}
