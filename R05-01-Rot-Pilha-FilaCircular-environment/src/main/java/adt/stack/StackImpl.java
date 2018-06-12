package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T topElement;
		if (isEmpty()) {
			topElement = null;
		} else {
			topElement = array[top];
		}
		return topElement;
	}

	@Override
	public boolean isEmpty() {
		boolean answer = false;
		if (top == -1) {
			answer = true;
		}
		return answer;
	}

	@Override
	public boolean isFull() {
		boolean answer = false;
		if (top == array.length - 1) {
			answer = true;
		}
		return answer;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		checkStackOverflow();

		if (element != null) {
			top += 1;
			array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		checkStackUnderflow();

		T topElement = array[top];
		top -= 1;

		return topElement;
	}

	private void checkStackUnderflow() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
	}

	private void checkStackOverflow() throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
	}
}
