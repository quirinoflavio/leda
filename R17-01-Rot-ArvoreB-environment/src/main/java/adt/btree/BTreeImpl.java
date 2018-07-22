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
		int cont = node.getElements().size();
		
		for(BNode<T> children : node.getChildren()) {
			cont += size(children);
		}
		
		return cont;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(root, element);
	}
	
	private BNodePosition<T> search(BNode<T> node, T element) {
		BNodePosition<T> result = new BNodePosition<T>();
		int i = 0;
		while(i < node.size() && element.compareTo(node.getElementAt(i)) > 0) {
			i++;
		}
		
		if(i < node.size() && element.compareTo(node.getElementAt(i)) == 0) {
			result = new BNodePosition<T>(node, i);
		}
		else if(!node.isLeaf()){
			result = search(node.getChildren().get(i), element);
		}
		
		return result;
	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			if(isEmpty()) {
				root.addElement(element);
			}
			else {			
				BNode<T> node = this.root;
				
				while (!node.isLeaf()) {
					int i = 0;
					while (i < node.getElements().size() && element.compareTo(node.getElementAt(i)) > 0) {
						i++;
					}

					node = node.getChildren().get(i);
				}
				
				if (node.isFull()) {
					node.addElement(element);
					this.split(node);
				}
				else {
					node.addElement(element);
				}
			}
		}
	}
	
	private void split(BNode<T> node) {
		int mid = node.getElements().size() / 2;
		BNode<T> newNode = new BNode<T>(this.root.getOrder());
		
		for (int i = mid + 1; i < node.getElements().size(); i ++) {
			newNode.addElement(node.getElementAt(i));
		}
		
		newNode.setParent(node.getParent());
		
		this.promote(node, newNode);
		
		for (T element : newNode.getElements()) {
			node.removeElement(element);
		}
	}
	
	private void promote(BNode<T> node, BNode<T> newNode) {
		int mid = node.getElements().size() / 2;
		T element = node.getElementAt(mid);
		
		if (node.getParent() == null) {
			BNode<T> newRoot = new BNode<T>(this.root.getOrder());
			newRoot.addElement(element);
			newRoot.addChild(0, node);
			newRoot.addChild(1, newNode);
			
			if (!node.isLeaf()) {
				int position = 0;
				for (int i = mid + 1; i < node.getChildren().size(); i++) {
					newNode.addChild(position, node.getChildren().get(i));
					position ++;
				}
				for (int i = node.getChildren().size() - 1; i > mid; i--) {
					node.removeChild(node.getChildren().get(i));
				}
			}			
			this.root = newRoot;
		}
		else {
			BNode<T> parent = node.getParent();
			parent.addElement(element);
			int idxPromote = parent.indexOfChild(node);
			parent.addChild(idxPromote + 1, newNode);
			
			if (!node.isLeaf()) {
				int position = 0;
				for (int i = mid + 1; i < node.getChildren().size(); i++) {
					newNode.addChild(position, node.getChildren().get(i));
					position ++;
				}
				for (int i = node.getChildren().size() - 1; i > mid; i--) {
					node.removeChild(node.getChildren().get(i));
				}
			}
			
			if (parent.getElements().size() == this.order) {
				this.split(parent);
			}
		}
		
		node.removeElement(mid);
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
	
	//M
	//m<>;.,:


}
