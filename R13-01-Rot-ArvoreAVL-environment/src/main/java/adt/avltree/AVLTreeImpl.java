package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

   // TODO Do not forget: you must override the methods insert and remove
   // conveniently.

   // AUXILIARY
   protected int calculateBalance(BSTNode<T> node) {
      int balance = 0;
      if (!node.isEmpty()) {
         if (node.getLeft() instanceof BSTNode && node.getRight() instanceof BSTNode) {
            balance = Math.abs(height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight()));
         }
      }

      return balance;
   }

   // AUXILIARY
   protected void rebalance(BSTNode<T> node) {
      if (!node.isEmpty()) {
         int rootBalance = calculateBalance(node);
         int leftBalance = calculateBalance((BSTNode<T>) node.getLeft());
         int rightBalance = calculateBalance((BSTNode<T>) node.getRight());

         //left-left
         //right-right
         //right-left
         //left-right

      }
   }

   // AUXILIARY
   protected void rebalanceUp(BSTNode<T> node) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }
}
