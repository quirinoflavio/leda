package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		checkQueueOverflow();
		if (element != null) {
			tail = (tail + 1) % array.length;
			array[tail] = element;
			elements += 1;
			if (head == -1) {
				head += 1;
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		checkQueueUnderflow();

		T oldestElement = head();
		head = (head + 1) % array.length;
		elements -= 1;

		return oldestElement;
	}

	@Override
	public T head() {
		T oldestElement;
		if (isEmpty()) {
			oldestElement = null;
		} else {
			oldestElement = array[head];
		}
		return oldestElement;
	}

	@Override
	public boolean isEmpty() {
		boolean answer = false;
		if (elements == 0) {
			answer = true;
		}
		return answer;
	}

	@Override
	public boolean isFull() {
		boolean answer = false;
		if (elements == array.length) {
			answer = true;
		}
		return answer;
	}

	private void checkQueueUnderflow() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
	}

	private void checkQueueOverflow() throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
	}
}
