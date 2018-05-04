package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */

public class SimultaneousBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {

   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (verificaArray(array, leftIndex, rightIndex)) {
         int left = leftIndex;
         int right = rightIndex;

         for (int pos = leftIndex; pos < rightIndex; pos++) {
            while (left < rightIndex && right > leftIndex) {
               if (array[left].compareTo(array[left + 1]) > 0) {
                  Util.swap(array, left, left + 1);
               }
               if (array[right].compareTo(array[right - 1]) < 0) {
                  Util.swap(array, right, right - 1);
               }
               left++;
               right--;
            }

            left = leftIndex + pos;
            right = rightIndex - pos;
         }
      }
   }

   private boolean verificaArray(T[] array, int leftIndex, int rightIndex) {
      boolean ehValido;
      if (array != null && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
         ehValido = true;
      } else {
         ehValido = false;
      }
      return ehValido;
   }
}