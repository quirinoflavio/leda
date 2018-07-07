package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

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

	@Override
	protected void insert(BSTNode<T> root, T element) {
		if (root.isEmpty()) {
			root.setData(element);
			root.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
			root.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
		} 
		else {
			if (root.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) root.getLeft(), element);
			} 
			else {
				insert((BSTNode<T>) root.getRight(), element);
			}
			rebalance(root);
		}
	}

	@Override
	protected void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
			rebalanceUp(node);
		}
		else {
			BSTNode<T> auxNode;
			if (!node.getRight().isEmpty()) {
				auxNode = minimum((BSTNode<T>) node.getRight());
			} 
			else {
				auxNode = maximum((BSTNode<T>) node.getLeft());
			}
			node.setData(auxNode.getData());
			remove(auxNode);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight()); 
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int rootBalance = calculateBalance(node);
			int leftBalance = calculateBalance((BSTNode<T>) node.getLeft());
			int rightBalance = calculateBalance((BSTNode<T>) node.getRight());
			
			// left-left
			if (rootBalance > 1 && leftBalance >= 0) {
				Util.rightRotation(node);
			}

			// right-right
			else if (rootBalance < -1 && rightBalance <= 0) {
				Util.leftRotation(node);
			}

			// left-right
			else if (rootBalance > 1 && leftBalance < 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				Util.rightRotation(node);
			}

			// right-left
			else if (rootBalance < -1 && rightBalance > 0) {
				Util.rightRotation((BSTNode<T>) node.getRight());
				Util.leftRotation(node);
			}
		}
	}

	
	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}

	}
}
