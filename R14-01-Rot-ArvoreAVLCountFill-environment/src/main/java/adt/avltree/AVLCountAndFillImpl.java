package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int rootBalance = calculateBalance(node);
			int leftBalance = calculateBalance((BSTNode<T>) node.getLeft());
			int rightBalance = calculateBalance((BSTNode<T>) node.getRight());

			// left-left
			if (rootBalance > 1 && leftBalance >= 0) {
				RRcounter++;
				Util.rightRotation(node);
			}

			// right-right
			else if (rootBalance < -1 && rightBalance <= 0) {
				LLcounter++;
				Util.leftRotation(node);
			}

			// left-right
			else if (rootBalance > 1 && leftBalance < 0) {
				LLcounter++;
				RRcounter++;
				Util.leftRotation((BSTNode<T>) node.getLeft());
				Util.rightRotation(node);
			}

			// right-left
			else if (rootBalance < -1 && rightBalance > 0) {
				RRcounter++;
				LLcounter++;
				Util.rightRotation((BSTNode<T>) node.getRight());
				Util.leftRotation(node);
			}
		}
	}
	
	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	//adiciona os elementos na arvore sempre pegando o elemento do meio do array (ordenado);
	@Override
	public void fillWithoutRebalance(T[] array) {
		cleanAVL();
		if(array != null) {
			Arrays.sort(array);
			int leftMid = array.length/2;
			int rightMid = array.length/2 +1;
				
				
		}
	}
	
	private void fill(T[] array, int left, int middle, int right) {
		T[] arraySorted = array;
		
	}
	
	private void cleanAVL() {
		LLcounter = 0;
		LRcounter = 0;
		RRcounter = 0;
		RLcounter = 0;
		this.root = new BSTNode<T>();
	}

}
