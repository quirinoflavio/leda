package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot, particionamos
	 * o array colocando os menores a esquerda do pivot e os maiores a direita do
	 * pivot, e depois aplicamos a mesma estrategia recursivamente na particao a
	 * esquerda do pivot e na particao a direita do pivot.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do
	 * quicksort pode ser otimizada para lidar de forma mais eficiente com isso.
	 * Essa melhoria eh conhecida como quicksort tree way e consiste da seguinte
	 * ideia: - selecione o pivot e particione o array de forma que * arr[l..i]
	 * contem elementos menores que o pivot * arr[i+1..j-1] contem elementos iguais
	 * ao pivot. * arr[j..r] contem elementos maiores do que o pivot.
	 * 
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh
	 * feito recursivamente.
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaArray(array, leftIndex, rightIndex)) {
			int pivotIndex = leftIndex;
			T pivot = array[pivotIndex];
			int i = leftIndex + 1;
			int lower = leftIndex;
			int greater = rightIndex;

			while (i <= greater) {
				if (array[i].compareTo(pivot) < 0) {
					Util.swap(array, lower, i);
					lower++;
					i++;
				} else if (array[i].compareTo(pivot) > 0) {
					Util.swap(array, greater, i);
					greater--;
				}

				else {
					i++;
				}
			}

			sort(array, leftIndex, lower - 1);
			sort(array, greater + 1, rightIndex);
		}
	}

	private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
		boolean ehValido = false;

		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length
				&& array.length != 0) {
			ehValido = true;
		}

		return ehValido;
	}

}
