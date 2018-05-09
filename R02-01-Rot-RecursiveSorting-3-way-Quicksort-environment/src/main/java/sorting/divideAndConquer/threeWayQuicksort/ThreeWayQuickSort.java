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

			
			int greater = partition(array, leftIndex, rightIndex);
			int lower = partitionLower(array, leftIndex, greater);
			
			sort(array, leftIndex, lower);
			sort(array, greater + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex + 1;
		int j = rightIndex;
		T pivot = array[leftIndex];

		while (i <= j) {
			if (array[i].compareTo(pivot) <= 0) {
				i++;
			} else if (array[j].compareTo(pivot) > 0) {
				j--;
			} else {
				Util.swap(array, i, j);
			}
		}

		Util.swap(array, leftIndex, j);
		return j;
	}
	
	private int partitionLower(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex;
		int j = rightIndex -1;
		T pivot = array[rightIndex];
		
		while(i <= j) {
			if(array[i].compareTo(pivot) < 0) {
				i++;
			}else if (array[j].compareTo(pivot) == 0) {
				j--;
			}else {
				Util.swap(array, i, j);
				
			}
		}
		return j;
	}

	private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
		boolean ehValido = false;

		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			ehValido = true;
		}

		return ehValido;
	}

}
