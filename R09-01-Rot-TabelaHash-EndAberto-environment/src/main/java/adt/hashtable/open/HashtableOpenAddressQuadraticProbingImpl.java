package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;



public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	private final DELETED DEL = new DELETED();
	
	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}
	
	@Override
	public void insert(T element) {
		//if(elements + 1 >= this.size()) {
		//	rehash();
		//}
		int i = -1, j;
		do {
			i++;
			j = hashIndex(element, i);
		} while (i != this.size() && table[j] != null && !DEL.equals(table[j]) && !table[j].equals(element));

		if (table[j] == null || DEL.equals(table[j])) {
			table[j] = element;
			COLLISIONS+=i;
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		int i = -1, j;
		do {
			i++;
			j = hashIndex(element, i);
		} while (i != this.size() && table[j] != null && !element.equals(table[j]));

		if (i != this.size() && table[j] != null && table[j].equals(element)) {
			table[j] = DEL;
			elements--;
		}
	}

	@Override
	public T search(T element) {
		int i = -1, j;
		T result = null;
		do {
			i++;
			j = hashIndex(element, i);
		} while (i != this.size() && table[j] != null && !element.equals(table[j]));

		if (table[j] != null && element.equals(table[j])) {
			result = (T) table[j];
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int i = -1, j;
		int result = -1;
		do {
			i++;
			j = hashIndex(element, i);
		} while (i != this.size() && table[j] != null && !table[j].equals(element));

		if (i != this.size() && table[j] != null && table[j].equals(element)) {
			result = j;
		}
		return result;
	}

	private int hashIndex(T element, int probe) {
		int hashIndex = -1;
		if (hashFunction instanceof HashFunctionOpenAddress) {
			hashIndex = ((HashFunctionOpenAddress) hashFunction).hash(element, probe);
		}
		return hashIndex;
	}
}
