package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

   protected DoubleLinkedList<T> list;
   protected int size;

   public QueueDoubleLinkedListImpl(int size) {
      this.size = size;
      this.list = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (isFull()) {
         throw new QueueOverflowException();
      }
      list.insert(element);
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      if (isEmpty()) {
         throw new QueueUnderflowException();
      }

      T removed = ((DoubleLinkedListImpl<T>) list).getHead().getData();
      list.removeFirst();

      return removed;
   }

   @Override
   public T head() {
      return ((DoubleLinkedListImpl<T>) list).getHead().getData();
   }

   @Override
   public boolean isEmpty() {
      boolean answer;
      if (list.size() == 0) {
         answer = true;
      } else {
         answer = false;
      }
      return answer;
   }

   @Override
   public boolean isFull() {
      boolean answer;
      if (list.size() == size) {
         answer = true;
      } else {
         answer = false;
      }
      return answer;
   }

}
