package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaArray(array, leftIndex, rightIndex)) {

			int gap = rightIndex - leftIndex + 1;
			int swaps = 1;

			while (gap > 1 || swaps > 0) {

				gap = (int) (gap / 1.25);

				if (gap < 1) {
					gap = 1;
				}

				int i = leftIndex;
				swaps = 0;

				while (i + gap <= rightIndex) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						swaps = 1;
					}
					i++;
				}

			}

		}
	}

	private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
		boolean ehValido = false;

		if (array != null && array.length > 0 && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			ehValido = true;
		}

		return ehValido;
	}
}
