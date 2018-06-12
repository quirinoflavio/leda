package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

   protected SingleLinkedListNode<T> head;

   public SingleLinkedListImpl() {
      this.head = new SingleLinkedListNode<T>();
   }

   @Override
   public boolean isEmpty() {
      boolean answer = false;
      if (getHead().isNIL()) {
         answer = true;
      }
      return answer;
   }

   @Override
   public int size() {
      SingleLinkedListNode<T> auxHead = getHead();
      int listSize = 0;
      while (!auxHead.isNIL()) {
         listSize += 1;
         auxHead = auxHead.getNext();
      }
      return listSize;
   }

   @Override
   public T search(T element) {
      T answer;
      if (element == null) {
         answer = null;
      } else {
         SingleLinkedListNode<T> auxHead = getHead();

         while (!auxHead.isNIL() && !element.equals(auxHead.getData())) {
            auxHead = auxHead.getNext();
         }
         answer = auxHead.getData();
      }
      return answer;
   }

   @Override
   public void insert(T element) {
      if (element != null) {

         SingleLinkedListNode<T> newHead;
         SingleLinkedListNode<T> auxHead = getHead();

         if (auxHead.isNIL()) {
            newHead = new SingleLinkedListNode<T>(element, auxHead);
            setHead(newHead);
         } else {
            while (!auxHead.getNext().isNIL()) {
               auxHead = auxHead.getNext();
            }

            newHead = new SingleLinkedListNode<T>(element, auxHead.getNext());
            auxHead.setNext(newHead);
         }
      }
   }

   @Override
   public void remove(T element) {
      if (element != null) {

         if (getHead().getData().equals(element)) {
            setHead(getHead().getNext());
         } else {
            SingleLinkedListNode<T> auxHead = getHead();
            SingleLinkedListNode<T> previous = auxHead;

            while (!auxHead.getData().equals(element) && !auxHead.isNIL()) {
               previous = auxHead;
               auxHead = auxHead.getNext();
            }

            if (!auxHead.isNIL()) {
               previous.setNext(auxHead.getNext());
            }
         }
      }

   }

   @Override
   public T[] toArray() {
      SingleLinkedListNode<T> auxHead = getHead();
      T[] array = (T[]) new Object[size()];
      int count = 0;

      while (!auxHead.isNIL()) {
         array[count] = auxHead.getData();
         auxHead = auxHead.getNext();
         count += 1;
      }
      return array;
   }

   public SingleLinkedListNode<T> getHead() {
      return head;
   }

   public void setHead(SingleLinkedListNode<T> head) {
      this.head = head;
   }

}
