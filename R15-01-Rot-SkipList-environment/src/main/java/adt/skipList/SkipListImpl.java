package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		if(newValue != null && height > 0) {
			SkipListNode<T>[] update = updateList(key);
			SkipListNode<T> node = update[0].getForward(0);

			if (node.getKey() == key) {
				node.setValue(newValue);
			} 
			else {
				if (height > maxHeight) {
					throw new RuntimeException();
				}
				node = new SkipListNode<T>(key, height, newValue);
				for (int i = 0; i < height; i++) {
					node.getForward()[i] = update[i].getForward(i);
					update[i].getForward()[i] = node;
				}
			}
		}
	}
	
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = updateList(key);
		SkipListNode<T> node  = update[0].getForward(0);
		
		if(node.getKey() == key) {
			int i = 0;
			while(i < node.height() && update[i].getForward()[i].equals(node)) {
				update[i].getForward()[i] = node.getForward(i);
				i++;
			}
		}
	}
	
	private SkipListNode<T>[] updateList(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = this.root;
		
		for(int i = maxHeight-1; i >= 0; i--) {
			while(node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			update[i] = node;
		}
		return update;
	}
	
	
	
	@Override
	public int height() {
		int height = 0;
		
		for(int i = 0; i < root.height(); i++) {
			if(!root.getForward()[i].equals(NIL)) {
				height++;
			}
		}
		
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = root;
		
		for(int i = maxHeight-1; i >= 0; i--) {
			while(node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
		}
		
		node = node.getForward(0);
		
		if(node.getKey() != key) {
			node = null;
		}
		
		return node;
	}
	

	@Override
	public int size() {
		SkipListNode<T> node = root.getForward()[0];
		int size = 0;
		
		while(node != null && !node.equals(NIL)) {
			size++;
			node = node.getForward()[0];
		}
		
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size()+2];
		SkipListNode<T> node = root;
		int i = 0;
		
		while(node != null) {
			array[i] = node;
			node = node.getForward()[0];
			i++;
		}
		
		return array;
	}

}
