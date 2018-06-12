package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T oldestElement;
		if (isEmpty()) {
			oldestElement = null;
		} else {
			oldestElement = array[0];
		}
		return oldestElement;
	}

	@Override
	public boolean isEmpty() {
		boolean answer = false;
		if (tail == -1) {
			answer = true;
		}
		return answer;
	}

	@Override
	public boolean isFull() {
		boolean answer = false;
		if (tail == array.length - 1) {
			answer = true;
		}
		return answer;
	}

	private void shiftLeft() {
		for (int indice = 0; indice < tail; indice++) {
			array[indice] = array[indice + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		checkQueueOverflow();

		if (element != null) {
			tail += 1;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		checkQueueUnderflow();

		T oldestElement = head();
		shiftLeft();
		tail -= 1;

		return oldestElement;
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
