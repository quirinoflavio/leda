package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar
	 * apenas o comparator e mandar reordenar a heap usando esse comparator. Assim
	 * os metodos da heap não precisam saber se vai funcionar como max-heap ou
	 * min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}
	
	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (T elem : this.heap) {
			if(elem != null)
				resp.add(elem);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if (isValidIndex(position)) {
			int left = left(position);
			int right = right(position);
			int highest = position;

			if (isValidIndex(left) && comparator.compare(heap[highest], heap[left]) < 0) {
				highest = left(position);
			}

			if (isValidIndex(right) && comparator.compare(heap[highest], heap[right]) < 0) {
				highest = right(position);
			}

			if (highest != position) {
				Util.swap(heap, position, highest);
				heapify(highest);
			}
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if (element != null) {
			heap[size()] = element;
			index++;
			update();
		}
	}
	
	private void update() {
		int parent = parent(size() - 1);
		int newElementIndex = size() - 1;

		while (parent >= 0 && comparator.compare(heap[newElementIndex], heap[parent]) > 0) {
			Util.swap(heap, parent, newElementIndex);
			newElementIndex = parent;
			parent = parent(parent);
		}

	}

	@Override
	public void buildHeap(T[] array) {
		if (array != null) {
			cleanHeap();

			for (T element : array) {
				if(element != null) {
					insert(element);
				}
			}
		}
	}

	private void cleanHeap() {
		index = -1;
	}

	@Override
	public T extractRootElement() {
		T removedElement = null;
		
		if (!isEmpty()) {
			removedElement = heap[0];
			Util.swap(heap, index, 0);
			heap[index] = null;
			index--;
			heapify(0);
		}
		
		return removedElement;
	}

	@Override
	public T rootElement() {
		T element;
		
		if (isEmpty()) {
			element = null;
		} 
		else {
			element = heap[0];
		}

		return element;
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] sortedArray = null;
		
		if (array != null) {
			buildHeap(array);
			sortedArray = (T[]) new Comparable[size()];
			
			if (isMaxHeap()) {
				sortedArray = heapsortMaxHeap(sortedArray);
			} 
			else {
				sortedArray = heapsortMinHeap(sortedArray);
			}
		}
		
		return sortedArray;
	}
	
	private T[] heapsortMinHeap(T[] sortedArray) {
		int i = 0;
		
		while (!isEmpty()) {
			sortedArray[i] = extractRootElement();
			i++;
		}
		
		return sortedArray;
	}

	private T[] heapsortMaxHeap(T[] sortedArray) {
		int i = index;
		
		while (!isEmpty()) {
			sortedArray[i] = extractRootElement();
			i--;
		}
		
		return sortedArray;
	}
	
	private boolean isMaxHeap() {
		T max = rootElement();
		boolean isMaxHeap = true;
		int index = 0;
		
		while(index < size()) { //percorrendo a heap da esquerda para a direita
			if(getHeap()[index].compareTo(max) > 0) {
				isMaxHeap = false;
				break;
			}
			index++;
		}
		
		return isMaxHeap;
	}
	

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}
	
	private boolean isValidIndex(int index) {
		return index >= 0  && index < size();
	}

}
