package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size of
	 * the table to an integer that is prime. This can be achieved by producing such
	 * a prime number that is bigger and close to the desired size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as
	 * documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number greater
	 * than the given size (or the given size, if it is already prime). For example,
	 * if size=10 then the length must be 11. If size=20, the length must be 23. You
	 * must implement this idea in the auxiliary method getPrimeAbove(int size) and
	 * use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. If the given number is prime, it is returned. You can use the method
	 * Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number) {
		while (!Util.isPrime(number)) {
			number++;
		}
		return number;
	}


	private LinkedList<T> getLinkedList(T element) {
		LinkedList<T> result = null;
		int hashIndex = getHashIndex(element);
		if (hashIndex != -1) {
			if (table[hashIndex] == null) {
				table[hashIndex] = new LinkedList<T>();
				result = (LinkedList<T>) table[hashIndex];
			} else {
				result = ((LinkedList<T>) table[hashIndex]);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null && search(element) == null) {
			if (getLinkedList(element).isEmpty()) {

				getLinkedList(element).addFirst(element);

			} else {
				getLinkedList(element).addFirst(element);
				COLLISIONS++;
			}
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (getLinkedList(element).contains(element)) {
				getLinkedList(element).remove(element);
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			if (getLinkedList(element).contains(element)) {
				result = element;
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		if (element != null) {
			if (getLinkedList(element).contains(element)) {
				index = getHashIndex(element);
			}
		}
		return index;
	}

	private int getHashIndex(T element) {
		int hashIndex = -1;
		if (hashFunction instanceof HashFunctionClosedAddress) {
			hashIndex = Math.abs(((HashFunctionClosedAddress<T>) hashFunction).hash(element));
		}
		return hashIndex;
	}
}
