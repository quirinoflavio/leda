package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (verificaArray(array, leftIndex, rightIndex)) {
         int max = maxValue(array, leftIndex, rightIndex);
         int min = minValue(array, leftIndex, rightIndex);
         int[] vetorTemp = new int[max - min + 1];
         int[] vetorAux = new int[array.length];

         for (int i = 0; i < vetorTemp.length; i++) {
            vetorTemp[i] = 0;
         }

         for (int j = leftIndex; j <= rightIndex; j++) {
            vetorTemp[array[j] - min]++;
         }

         for (int i = 0; i < vetorTemp.length - 1; i++) {
            vetorTemp[i + 1] = vetorTemp[i + 1] + vetorTemp[i];
         }

         for (int j = rightIndex; j >= leftIndex; j--) {
            vetorTemp[array[j] - min]--;
            vetorAux[vetorTemp[array[j] - min]] = array[j];
         }

         for (int i = leftIndex; i <= rightIndex; i++) {
            array[i] = vetorAux[i];

         }
      }
   }

   private int maxValue(Integer[] array, int leftIndex, int rightIndex) {
      int maxValue = Integer.MIN_VALUE;
      for (int m = leftIndex; m <= rightIndex; m++) {
         if (array[m] > maxValue) {
            maxValue = array[m];
         }
      }

      return maxValue;
   }

   private static int minValue(Integer[] array, int leftIndex, int rightIndex) {
      int minValue = Integer.MAX_VALUE;
      for (int m = leftIndex; m <= rightIndex; m++) {
         if (array[m] < minValue) {
            minValue = array[m];
         }
      }

      return minValue;
   }

   private boolean verificaArray(Integer[] array, int leftIndex, int rightIndex) {
      boolean ehValido = false;

      if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
         ehValido = true;
      }

      return ehValido;
   }
}
