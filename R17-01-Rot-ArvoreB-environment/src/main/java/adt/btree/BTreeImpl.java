package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int height = -1;
		if(!node.isEmpty()) {
			if(node.isLeaf()) {
				height = 0;
			}
			else {
				height = 1 + height(node.getChildren().getFirst());
			}
		}
		
		return height;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] array = new BNode[numOfNodes(getRoot())];
		depthLeftOrder(getRoot(), array);
		
		return array;
	}
	
	private int numOfNodes(BNode<T> node) {
		int answer = 1;
		if(!node.isLeaf()) {
			for(BNode<T> children : node.getChildren()) {
				answer += numOfNodes(children);
			}
		}
		
		return answer;
	}

	private void depthLeftOrder(BNode<T> node, BNode<T>[] array){
		add(array, node);
		for(BNode<T> children : node.getChildren()) {
			depthLeftOrder(children, array);
		}
	}

	private void add(BNode<T>[] array, BNode<T> node) {
		int i = 0;
		
		while(i < array.length && array[i] != null) {
			i++;
		}
		
		if(i < array.length) {
			array[i] = node;
		}
	}

	@Override
	public int size() {
		return size(getRoot());
	}
	
	private int size(BNode<T> node) {
		return 0;
	}

	@Override
	public BNodePosition<T> search(T element) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void insert(T element) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");

	}

	private void split(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
