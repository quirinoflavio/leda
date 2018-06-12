package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new StackOverflowException();
			} else {
				top.insertFirst(element);
			}
		}
	}
	
	@Override
	public T pop() throws StackUnderflowException {
		T aux;
		if(isEmpty()) {
			throw new StackUnderflowException();
		}else {
			aux = top();
			top.removeFirst();
		}
		return aux;
	}

	@Override
	public T top() {
		T result = null;
		T[] elements = top.toArray();
		if (elements.length != 0) {
			result = elements[0];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
