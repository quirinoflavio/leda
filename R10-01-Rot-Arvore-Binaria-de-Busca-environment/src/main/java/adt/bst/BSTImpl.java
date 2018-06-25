package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		int max;
		if (root.isEmpty()) {
			max = -1;
		} else {
			max = height(root);
		}
		return max;
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		int leftSide = height((BSTNode<T>) node.getLeft());
		int rightSide = height((BSTNode<T>) node.getRight());

		return Math.max(leftSide, rightSide) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return new BSTNode.Builder().build();
		}
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().compareTo(element) == 0) {
			return node;
		}
		if (node.getData().compareTo(element) < 0) {
			return search((BSTNode<T>) node.getRight(), element);
		}
		return search((BSTNode<T>) node.getLeft(), element);

	}

//	private BSTNode<T> search1(BSTNode<T> node, T element) {
//		if (node.isEmpty()) {
//			return new BSTNode.Builder().build();
//		} else {
//			if (node.getData().compareTo(element) == 0) {
//				return (BSTNode<T>) node;
//			} else {
//				if (node.getData().compareTo(element) < 0) {
//					return search((BSTNode<T>) node.getRight(), element);
//				} else {
//					return search((BSTNode<T>) node.getLeft(), element);
//				}
//			}
//		}
//	}

	@Override
	public void insert(T element) {
		auxInsert(root, element);
	}

	private void auxInsert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (node.getData().compareTo(element) > 0) {
				auxInsert((BSTNode<T>) node.getLeft(), element);
			} else if (node.getData().compareTo(element) < 0) {
				auxInsert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (root.isEmpty()) {
			return null;
		}
		return auxMaximum(root);
	}

	private BSTNode<T> auxMaximum(BSTNode<T> node) {
		while (!node.getRight().isEmpty()) {
			node = (BSTNode<T>) node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		if (root.isEmpty()) {
			return null;
		}
		return auxMinimum(root);
	}

	private BSTNode<T> auxMinimum(BSTNode<T> node) {
		while (!node.getLeft().isEmpty()) {
			node = (BSTNode<T>) node.getLeft();
		}
		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element), ans;
		if (node.isEmpty()) {
			ans = null;
		} else if (!node.getRight().isEmpty()) {
			ans = auxMinimum((BSTNode<T>) node.getRight());
		} else {

			BSTNode<T> parent = (BSTNode<T>) node.getParent();

			while (parent != null && isRightChild(node, parent)) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}

			ans = parent;
		}
		return ans;
	}

	private boolean isRightChild(BSTNode<T> child, BSTNode<T> parent) {
		return !parent.getRight().isEmpty() && parent.getRight().getData().compareTo(child.getData()) == 0;
	}
	
	private boolean isLeftChild(BSTNode<T> child, BSTNode<T> parent) {
		return !parent.getLeft().isEmpty() && parent.getLeft().getData().compareTo(child.getData()) == 0;
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element), ans;
		if (node.isEmpty()) {
			ans = null;
		} else if (!node.getLeft().isEmpty()) {
			ans = auxMaximum((BSTNode<T>) node.getLeft());
		} else {

			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			
			while (parent != null && isLeftChild(node, parent)) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}

			ans = parent;
		}

		return ans;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			
			if (node.isLeaf()) { // nó não tem nenhum filho
				node.setData(null);
				
			} else if (node.getLeft().isEmpty() ^ node.getRight().isEmpty()) {// nó tem apenas um filho
				if (node.getData().compareTo(root.getData()) != 0) {
					if (isLeftChild(node, (BSTNode<T>) node.getParent())) { // é filho a esquerda
						if (!node.getLeft().isEmpty()) { // node tem filho a esquerda
							node.getLeft().setParent(node.getParent());
							node.getParent().setLeft(node.getLeft());
						} else { // node tem filho a direita
							node.getRight().setParent(node.getParent());
							node.getParent().setLeft(node.getRight());
						}
					} else { // é filho a direita
						if (!node.getLeft().isEmpty()) { // tem filho a esquerda
							node.getLeft().setParent(node.getParent());
							node.getParent().setRight(node.getLeft());
						} else { // tem filho a direita
							node.getRight().setParent(node.getParent());
							node.getParent().setRight(node.getRight());
						}
					}
					
				} else {
					if (!root.getLeft().isEmpty()) {
						root.setData(root.getLeft().getData());
						root.setLeft(root.getLeft().getLeft());
						root.getLeft().setParent(root);
					} else {
						root.setData(root.getRight().getData());
						root.setRight(root.getRight().getRight());
						root.getRight().setParent(root);
					}
				}
				
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor.getData());
//				BSTNode<T> child = sucessor.getLeft() == null ? (BSTNode<T>) sucessor.getRight() : (BSTNode<T>) sucessor.getLeft();
//				if (!sucessor.getLeft().isEmpty())
//		        {
//		            if (sucessor.getParent().getLeft().getData().compareTo(sucessor.getData()) == 0)
//		            	sucessor.getParent().setLeft(child);
//		            else
//		            	sucessor.getParent().setRight(child);
//		        }
//		        else
//		        {
//		            if (sucessor.getParent().getLeft().getData().compareTo(sucessor.getData()) == 0)
//		            	sucessor.getParent().setLeft(child);
//		            else
//		            	sucessor.getParent().setRight(child);
//		        }
				
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];
		auxPreOrder(array, root);
		return array;
	}

	private void auxPreOrder(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {

			addToArray(array, node.getData());
			auxPreOrder(array, (BSTNode<T>) node.getLeft());
			auxPreOrder(array, (BSTNode<T>) node.getRight());
		}
	}

	private void addToArray(T[] array, T data) {
		int i = 0;
		while (i < array.length) {
			if (array[i] == null) {
				array[i] = data;
				break;
			}
			i++;
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];
		auxOrder(array, root);
		return array;
	}

	private void auxOrder(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			auxOrder(array, (BSTNode<T>) node.getLeft());
			addToArray(array, node.getData());
			auxOrder(array, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];
		auxPostOrder(array, root);
		return array;
	}

	private void auxPostOrder(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {

			auxPreOrder(array, (BSTNode<T>) node.getLeft());
			auxPreOrder(array, (BSTNode<T>) node.getRight());
			addToArray(array, node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
