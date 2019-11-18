package f1;
import java.nio.BufferOverflowException;
import java.util.EmptyStackException;

public class ArrayStack2<T> implements StackMC<T> {
    private T[] elements;
    private int size=0;
    
    public ArrayStack2(int capacity) {
        elements = (T[])(new Object[capacity]);
    }

    public StackMC<T> push(T element) {
    	if(size>=elements.length)
    		throw new BufferOverflowException();
        elements[ size ] = element;
        size++;
        return this;
    }

    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[size-1];
    }

    public boolean isEmpty() {
        return (size==0);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArrayStack2<Integer> stack = new ArrayStack2<Integer>(20);
        stack.push(10).push(23).push(8).push(11);
        

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
