package f6;
public class Counter2 {
    private int counter=0;
    
    public synchronized void incCounter() {
    	counter++;
    }
    
    public synchronized int getCounter() {
    	return counter;
    }
}