package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
   }

   @Override
   public T[] sort(T[] array) {
      cleanTree();
      fillTree(array);
      return order();
   }

   @Override
   public T[] reverseOrder() {
      T[] array = (T[]) new Comparable[size()];
      reverseOrder(array, root);
      return array;
   }

   private void reverseOrder(T[] array, BSTNode<T> root) {
      if (!root.isEmpty()) {
         reverseOrder(array, (BSTNode<T>) root.getRight());
         addToArray(array, root.getData());
         reverseOrder(array, (BSTNode<T>) root.getLeft());
      }
   }

   @Override
   protected void insert(BSTNode<T> root, T element) {
      if (root.isEmpty()) {
         root.setData(element);
         root.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
         root.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
      } else {
         if (comparator.compare(root.getData(), element) > 0) {
            insert((BSTNode<T>) root.getLeft(), element);
         } else if (comparator.compare(root.getData(), element) < 0) {
            insert((BSTNode<T>) root.getRight(), element);
         }
      }
   }

   @Override
   protected void remove(BSTNode<T> node) {
      if (node.isLeaf()) {
         node.setData(null);
         node.setLeft(null);
         node.setRight(null);
      } else {
         BSTNode<T> sucessor = sucessor(node);
         if (sucessor != null && comparator.compare(sucessor.getData(), node.getData()) != 0) {
            node.setData(sucessor.getData());
            remove(sucessor);
         } else {
            BSTNode<T> predecessor = predecessor(node);
            if (predecessor != null && comparator.compare(predecessor.getData(), node.getData()) != 0) {
               node.setData(predecessor.getData());
               remove(predecessor);
            }
         }
      }
   }

   @Override
   protected BSTNode<T> search(BSTNode<T> node, T element) {
      BSTNode<T> result;

      if (node.isEmpty()) {
         result = (BSTNode<T>) new BSTNode.Builder<T>().build(); // NIL isolado da arvore.
      } else if (node.getData().compareTo(element) == 0) {
         result = node;
      } else if (node.getData().compareTo(element) < 0) {
         result = search((BSTNode<T>) node.getRight(), element);
      } else {
         result = search((BSTNode<T>) node.getLeft(), element);
      }

      return result;
   }

   @Override
   protected boolean isRightChild(BSTNode<T> child, BSTNode<T> parent) {
      return !parent.getRight().isEmpty() && comparator.compare(parent.getRight().getData(), child.getData()) == 0;
   }

   @Override
   protected boolean isLeftChild(BSTNode<T> child, BSTNode<T> parent) {
      return !parent.getLeft().isEmpty() && comparator.compare(parent.getLeft().getData(), child.getData()) == 0;
   }

   private void fillTree(T[] array) {
      for (T i : array) {
         insert(i);
      }
   }

   private void cleanTree() {
      while (!isEmpty()) {
         remove(getRoot().getData());
      }
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

}
