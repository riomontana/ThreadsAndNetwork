package laboration1;

public class ArrayQueue<T> implements Queue<T> {

	private T[] elements;
	private int size = 0;

	public ArrayQueue(int capacity) {	
		elements = (T[]) new Object[capacity];
	}

	// lägger till ett objekt i kön
	public void add(T elem) {
		if(size > elements.length) {
			throw new QueueException("full kö");
		}
		elements[ size++ ] = elem;
	}
	// tar bort ett objekt från kön och returnerar referens till objektet
	public T remove() {
		if(size == 0) {
			throw new QueueException("tom kö");	
		}
		T value = elements[0];
		size--;
		for(int i = 1; i<size; i++) {
			elements[i-1] = elements[i];
		}
		elements[size] = null;
		return value;
	}

	// returnerar referens till objektet som är först i kön
	public T element() {
		if(size == 0) {
			throw new QueueException("tom kö");
		}
		return elements[0];
	}

	// returnerar true om kön är tom, annars false
	public boolean isEmpty() {
		return size == 0;
	}

	// returnerar antal objekt i kön
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		ArrayQueue <Integer> queue = new ArrayQueue <Integer>(10);
		Integer elem;
		for(int i=0; i<5; i++) {
			queue.add(new Integer(i));
		}
		while(!queue.isEmpty()) {
			elem = queue.remove();
			System.out.print(elem + " ");
		}
	}
}

