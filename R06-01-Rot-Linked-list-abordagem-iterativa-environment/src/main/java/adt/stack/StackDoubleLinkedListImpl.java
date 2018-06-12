package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

   protected DoubleLinkedList<T> top;
   protected int size;

   public StackDoubleLinkedListImpl(int size) {
      this.size = size;
      this.top = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (isFull()) {
         throw new StackOverflowException();
      }
      top.insertFirst(element);
   }

   @Override
   public T pop() throws StackUnderflowException {
      if (isEmpty()) {
         throw new StackUnderflowException();
      }
      T removed = ((DoubleLinkedListImpl<T>) top).getHead().getData();
      top.removeFirst();

      return removed;
   }

   @Override
   public T top() {
      return ((DoubleLinkedListImpl<T>) top).getHead().getData();
   }

   @Override
   public boolean isEmpty() {
      boolean answer;
      if (top.size() == 0) {
         answer = true;
      } else {
         answer = false;
      }
      return answer;
   }

   @Override
   public boolean isFull() {
      boolean answer;
      if (top.size() == size) {
         answer = true;
      } else {
         answer = false;
      }
      return answer;
   }

}
