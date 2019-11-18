package f5;
public class TimeSharing {
    public static void main(String[] args) {
//        Printer[] threads = {new Printer("A"),new Printer("B"),new Printer("C")};
        Printer[] threads = {new Printer("A",Thread.MIN_PRIORITY),
                new Printer("B",Thread.NORM_PRIORITY),
                new Printer("C",Thread.MAX_PRIORITY),
                new Printer("D",Thread.MIN_PRIORITY),
                new Printer("E",Thread.NORM_PRIORITY),
                new Printer("F",Thread.MAX_PRIORITY),
                new Printer("G",Thread.MAX_PRIORITY)}; 
                
        for(int i=0; i<threads.length; i++)
            threads[i].start();
    }
}

class Printer extends Thread {
    String id;
    int count;
    
    public Printer(String id) {
        this.id = id;
    }
    
    public Printer(String id, int prioritet) {
        this.id = id;
        setPriority(prioritet);
    }
    
    public void run() {
        while(count<100000000) {
            count++;
            if(count%10000000==0)
                System.out.println(id+" prioritet="+getPriority()+",  "+count);
        }
    }
}