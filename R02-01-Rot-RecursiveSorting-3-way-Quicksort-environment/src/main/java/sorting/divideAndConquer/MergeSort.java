package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaArray(array, leftIndex, rightIndex)) {
			int indiceMeio = ((leftIndex + rightIndex) / 2);

			sort(array, leftIndex, indiceMeio);
			sort(array, indiceMeio + 1, rightIndex);

			merge(array, leftIndex, indiceMeio, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int indiceMeio, int rightIndex) {
		T[] arrayAux = (T[]) new Comparable[rightIndex + 1];
		arrayAux = copy(array, rightIndex);

		int i = leftIndex;
		int m = indiceMeio + 1;
		int k = leftIndex;
		while (i <= indiceMeio && m <= rightIndex) {
			if (arrayAux[i].compareTo(arrayAux[m]) < 0) {
				array[k] = arrayAux[i];
				i++;
			} else {
				array[k] = arrayAux[m];
				m++;
			}
			k++;
		}

		while (i <= indiceMeio) {
			array[k] = arrayAux[i];
			i++;
			k++;
		}

		while (m <= rightIndex) {
			array[k] = arrayAux[m];
			m++;
			k++;
		}
	}

	private T[] copy(T[] array, int limiteDireita) {
		T[] arrayDeSaida = (T[]) new Comparable[limiteDireita + 1];
		for (int indice = 0; indice <= limiteDireita; indice++) {
			arrayDeSaida[indice] = array[indice];
		}
		return arrayDeSaida;
	}

	private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
		boolean ehValido = false;

		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			ehValido = true;
		}

		return ehValido;
	}
}
