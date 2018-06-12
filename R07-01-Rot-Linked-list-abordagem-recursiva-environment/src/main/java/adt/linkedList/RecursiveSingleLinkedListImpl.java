package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int result = 0;
		if (!isEmpty()) {
			result = 1 + getNext().size();
		}
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			if (!isEmpty()) {
				if (getData().equals(element)) {
					result = getData();
				} else {
					result = getNext().search(element);
				}
			}
		}
		return result;

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<T>());
			} else {
				getNext().insert(element);
			}
		}
	}

	//De alguma forma, o Override da RecursiveDLL afeta este metodo.
	//Sem implemmentar remove(T element) na RecursiveDLL, funciona tudo normal.
	//Porem, ao implamentar o remove na RecursiveDLL, os testes dessa classe
	//param de funcionar.
	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (getData().equals(element)) {
					setData(getNext().getData());
					setNext(getNext().getNext());
				} else {
					getNext().remove(element);
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		this.auxToArray(array, this, 0);
		return array;
	}

	private void auxToArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int pos) {
		if (!node.isEmpty()) {
			array[pos] = node.getData();
			auxToArray(array, node.getNext(), pos + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
