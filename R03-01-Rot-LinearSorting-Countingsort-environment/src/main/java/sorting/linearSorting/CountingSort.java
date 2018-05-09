package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int k = 0;
		for (int m = leftIndex; m < rightIndex; m++) {
			if (array[m] > k) {
				k = array[m];
			}
		}
		
		int[] vetorTemp = new int[k];
		
		for (int i = 0; i < k; i++) {
			vetorTemp[i] = 0;
		}
		
		for(int j = leftIndex; j < rightIndex; j++) {
			vetorTemp[array[j]]+=1;
		}
		
		for(int c = leftIndex; c < k; c++) {
			vetorTemp[c] = vetorTemp[c] + vetorTemp[c-1];
		}
		
		int[] vetorAux = new int[array.length];
		for(int v = rightIndex; v > leftIndex; v--) {
			vetorAux[vetorTemp[array[v]]] = array[v];
			vetorTemp[array[v]]--;
		}
		
		for(int r=leftIndex; r<rightIndex; r++) {
			array[r] = vetorAux[r];
		}
	}

}
