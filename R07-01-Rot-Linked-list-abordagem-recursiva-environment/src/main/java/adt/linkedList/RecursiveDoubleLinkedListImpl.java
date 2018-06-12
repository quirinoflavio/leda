package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));
				setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));
			} else {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>();
				aux.setData(getData());
				aux.setNext(getNext());
				aux.setPrevious(this);
				setData(element);
				setNext(aux);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (getNext().isEmpty()) {
				setData(null);
				setNext(null); 
				setPrevious(null);
			} else {
				setData(getNext().getData());
				setNext(getNext().getNext());
				if (getNext() instanceof RecursiveDoubleLinkedListImpl) {
					((RecursiveDoubleLinkedListImpl<T>) getNext().getNext()).setPrevious(this);
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (getNext().isEmpty()) {
				if (getPrevious().isEmpty()) {
					setData(null);
					setNext(null);
					setPrevious(null);
				} else {
					if (getNext() instanceof RecursiveSingleLinkedListImpl) {

						((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(getPrevious());
						getPrevious().setNext(getNext());
					}
				}
			} else {
				if (getNext() instanceof RecursiveSingleLinkedListImpl)
					((RecursiveDoubleLinkedListImpl<T>) getNext()).removeLast();
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));
				if (getPrevious() == null) {
					setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));
				}
			} else {
				getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (getData().equals(element)) {
					if (getPrevious().isEmpty() && getNext().isEmpty()) {
						setData(null);
						setNext(null);
						setPrevious(null);
					} else {
						if (getNext() instanceof RecursiveSingleLinkedListImpl) {
							setData(getNext().getData());
							((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(getPrevious());
							getPrevious().setNext(getNext());
						}
					}
				} else {
					getNext().remove(element);
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
