package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight(castNode(this.root));
   }

   private int blackHeight(RBNode<T> node) {
      int blackHeight = 0;

      if (!node.isEmpty()) {
         if (node.getColour() == Colour.BLACK) {
            int left = 1 + blackHeight(castNode(node.getLeft()));
            int right = 1 + blackHeight(castNode(node.getRight()));
            blackHeight = Math.max(left, right);
         } else {
            int left = blackHeight(castNode(node.getLeft()));
            int right = blackHeight(castNode(node.getRight()));
            blackHeight = Math.max(left, right);
         }
      }

      return blackHeight;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes(castNode(this.root));
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean verifyLeft = true;
      boolean verifyRight = true;
      if (!node.isEmpty()) {
         if (node.getColour() == Colour.RED
               && (castNode(node.getLeft()).getColour() == Colour.RED || castNode(node.getRight()).getColour() == Colour.RED)) {
            verifyLeft = false;
            verifyRight = false;
         } else {
            verifyLeft = verifyChildrenOfRedNodes(castNode(node.getLeft()));
            verifyRight = verifyChildrenOfRedNodes(castNode(node.getRight()));
         }
      }
      return verifyLeft && verifyRight;
   }

   /**
    * Verifies the black-height property from the root. The method blackHeight
    * returns an exception if the black heights are different.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight(castNode(root));
   }

   private boolean verifyBlackHeight(RBNode<T> node) {
      int left = 0;
      int right = 0;
      boolean verifyLeft = true;
      boolean verifyRight = true;

      if (!node.isEmpty()) {
         verifyLeft = verifyBlackHeight(castNode(node.getLeft()));
         left = blackHeight(castNode(node.getLeft()));

         verifyRight = verifyBlackHeight(castNode(node.getRight()));
         right = blackHeight(castNode(node.getRight()));
      }

      return left == right && verifyLeft && verifyRight;
   }

   @Override
   public void insert(T value) {
      if (value != null) {
         insert(castNode(root), value);
      }
   }

   private void insert(RBNode<T> root, T element) {
      if (root.isEmpty()) {
         root.setData(element);

         root.setLeft(new RBNode<T>());
         root.getLeft().setParent(root);

         root.setRight(new RBNode<T>());
         root.getRight().setParent(root);

         root.setColour(Colour.RED);
         fixUpCase1(root);
      } else {
         if (root.getData().compareTo(element) > 0) {
            insert(castNode(root.getLeft()), element);
         } else {
            insert(castNode(root.getRight()), element);
         }
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[size()];
      preOrderRecursive(array, castNode(getRoot()));
      return array;
   }

   private void preOrderRecursive(RBNode<T>[] array, RBNode<T> node) {
      if (!node.isEmpty()) {
         addToArray(array, node);
         preOrderRecursive(array, castNode(node.getLeft()));
         preOrderRecursive(array, castNode(node.getRight()));
      }
   }

   private void addToArray(RBNode<T>[] array, RBNode<T> node) {
      int i = 0;

      while (i < array.length) {
         if (array[i] == null) {
            array[i] = node;
            break;
         }

         i++;
      }
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.getParent() == null) {
         node.setColour(Colour.BLACK);
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (castNode(node.getParent()).getColour() == Colour.RED) {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      if (getUncle(node).getColour() == Colour.RED) {
         castNode(node.getParent()).setColour(Colour.BLACK);
         getUncle(node).setColour(Colour.BLACK);
         castNode(node.getParent().getParent()).setColour(Colour.RED);
         fixUpCase1(castNode(node.getParent().getParent()));
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      if (isRightChild(castNode(node), castNode(node.getParent()))
            && isLeftChild(castNode(node.getParent()), castNode(node.getParent().getParent()))) {
         Util.leftRotation((BSTNode<T>) node.getParent());
      } else if (isLeftChild(castNode(node), castNode(node.getParent()))
            && isRightChild(castNode(node.getParent()), castNode(node.getParent().getParent()))) {
         Util.rightRotation((BSTNode<T>) node.getParent());
      }

      fixUpCase5(node);
   }

   protected void fixUpCase5(RBNode<T> node) {
      castNode(node.getParent()).setColour(Colour.BLACK);
      castNode(node.getParent().getParent()).setColour(Colour.RED);
      if (isLeftChild(castNode(node), castNode(node.getParent()))) {
         Util.rightRotation((BSTNode<T>) node.getParent().getParent());
      } else {
         Util.leftRotation((BSTNode<T>) node.getParent().getParent());
      }

   }

   private RBNode<T> getUncle(RBNode<T> node) {
      RBNode<T> parent = castNode(node.getParent());
      RBNode<T> uncle;
      if (isLeftChild(castNode(node.getParent()), castNode(node.getParent().getParent()))) {
         uncle = castNode(parent.getParent().getRight());
      } else {
         uncle = castNode(parent.getParent().getLeft());
      }

      return uncle;

   }

   protected boolean isRightChild(RBNode<T> child, RBNode<T> parent) {
      boolean answer = false;
      if (child != null && parent != null) {
         answer = !parent.getRight().isEmpty() && parent.getRight().getData().compareTo(child.getData()) == 0;
      }
      return answer;
   }

   protected boolean isLeftChild(RBNode<T> child, RBNode<T> parent) {
      boolean answer = false;
      if (child != null && parent != null) {
         answer = !parent.getLeft().isEmpty() && parent.getLeft().getData().compareTo(child.getData()) == 0;
      }
      return answer;
   }

   protected RBNode<T> castNode(Object object) {
      RBNode<T> node = null;
      if (object instanceof RBNode<?>) {
         node = (RBNode<T>) object;
      }
      return node;
   }
}
