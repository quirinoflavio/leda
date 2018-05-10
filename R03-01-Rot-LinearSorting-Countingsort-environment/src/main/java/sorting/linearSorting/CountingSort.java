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
      if (verificaArray(array, leftIndex, rightIndex)) {
         int max = maxValue(array, leftIndex, rightIndex);
         int[] vetorTemp = new int[max + 1];
         int[] vetorAux = new int[array.length];

         for (int i = 0; i <= max; i++) {
            vetorTemp[i] = 0;
         }

         for (int j = leftIndex; j <= rightIndex; j++) {
            vetorTemp[array[j]]++;
         }

         for (int i = 0; i < max; i++) {
            vetorTemp[i + 1] = vetorTemp[i + 1] + vetorTemp[i];
         }

         for (int j = rightIndex; j >= leftIndex; j--) {
            vetorTemp[array[j]]--;
            vetorAux[vetorTemp[array[j]]] = array[j];
         }

         for (int i = leftIndex; i <= rightIndex; i++) {
            array[i] = vetorAux[i];

         }
      }
   }

   private int maxValue(Integer[] array, int leftIndex, int rightIndex) {
      int maxValue = 0;
      for (int m = leftIndex; m <= rightIndex; m++) {
         if (array[m] > maxValue) {
            maxValue = array[m];
         }
      }

      return maxValue;
   }

   private boolean verificaArray(Integer[] array, int leftIndex, int rightIndex) {
      boolean ehValido = false;

      if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
         ehValido = true;
      }

      return ehValido;
   }

}
