package adt.bt;

import adt.bst.BSTNode;
import adt.rbtree.RBNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		if (!node.isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) node.getRight(); // pivot que será o
																// novo root

			BSTNode<T> t1 = (BSTNode<T>) node.getLeft(); // sub-arvore à
															// esquerda do root

			BSTNode<T> t2 = (BSTNode<T>) pivot.getLeft(); // sub-arvore à
															// esquerda do pivot

			BSTNode<T> t3 = (BSTNode<T>) pivot.getRight(); // sub-arvore à
															// direita do pivot

			node.setLeft(pivot); // copia o pivot para a esquerda do root

			// swap data do node com pivot para que o pivot agora seja o root
			T auxSwap = node.getData();
			BSTNode<T> auxC = new RBNode<T>();
			auxC.setData(node.getData());
			((RBNode<T>) auxC).setColour(((RBNode<T>) node).getColour());

			node.setData(pivot.getData());
			((RBNode<T>) node).setColour(((RBNode<T>) pivot).getColour());

			pivot.setData(auxSwap);
			((RBNode<T>) pivot).setColour(((RBNode<T>) auxC).getColour());

			// seta as sub-arvores às suas novas posições atualizando o parent
			pivot.setLeft(t1);
			t1.setParent(pivot);
			pivot.setRight(t2);
			t2.setParent(pivot);
			node.setRight(t3);
			t3.setParent(node);
		}

		return node;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if (!node.isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) node.getLeft(); // pivot que será o
															// novo root

			BSTNode<T> t1 = (BSTNode<T>) node.getRight(); // sub-arvore à
															// direito do root
			BSTNode<T> t2 = (BSTNode<T>) pivot.getRight(); // sub-arvore à
															// direita do pivot
			BSTNode<T> t3 = (BSTNode<T>) pivot.getLeft(); // sub-arvore à
															// esquerda do pivot

			node.setRight(pivot); // copia o pivot para a direita do root

			// swap data do node com pivot para que o pivot agora seja o root
			T auxSwap = node.getData();
			BSTNode<T> auxC = new RBNode<T>();
			auxC.setData(node.getData());
			((RBNode<T>) auxC).setColour(((RBNode<T>) node).getColour());

			node.setData(pivot.getData());
			((RBNode<T>) node).setColour(((RBNode<T>) pivot).getColour());

			pivot.setData(auxSwap);
			((RBNode<T>) pivot).setColour(((RBNode<T>) auxC).getColour());

			// seta as sub-arvores às suas novas posições atualizando o parent
			node.setLeft((BTNode<T>) t3);
			t3.setParent(node);
			pivot.setLeft((BTNode<T>) t2);
			t2.setParent(pivot);
			pivot.setRight((BTNode<T>) t1);
			t1.setParent(pivot);
		}

		return node;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
