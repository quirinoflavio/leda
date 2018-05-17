package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaArray(array, leftIndex, rightIndex)) {

			int pivotIndex = leftIndex + 1;

			while (pivotIndex <= rightIndex) {
				if (array[pivotIndex].compareTo(array[pivotIndex - 1]) >= 0) {
					pivotIndex++;
				} else {
					Util.swap(array, pivotIndex, pivotIndex - 1);
					if (pivotIndex > leftIndex + 1) {
						pivotIndex--;
					} else {
						pivotIndex++;
					}
				}
			}

		}

	}

	private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
		boolean ehValido = false;

		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			ehValido = true;
		}

		return ehValido;
	}
}
