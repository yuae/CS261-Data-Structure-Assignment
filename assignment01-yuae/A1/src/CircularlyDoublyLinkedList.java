
//mostly directly copied from CircularLinkedList.java

public class CircularlyDoublyLinkedList<E>
{
    private static class Node<E>
    {
        private E element;
        private Node<E> prev;//add prev reference to the circularly link sample
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n)//add prev parameter to the constructor
        {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement()
        {return element;}

        //add get&set methods for the prev node
        public Node<E> getPrev()
        {return prev;}

        public void setPrev(Node<E> p)
        {prev = p;}

        public Node<E> getNext()
        {return next;}

        public void setNext(Node<E> n)
        {next = n;}
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyDoublyLinkedList(){}

    public int size()
    {return size;}

    public boolean isEmpty()
    {return size == 0;}

    public E first()
    {
        if (isEmpty()) return null;
        else if(size == 1) return tail.getElement();
        return tail.getNext().getElement();
    }

    public E last()
    {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate()
    {
        if (tail != null)
            tail = tail.getNext();
    }
    //implement rotate backward method
    public void rotateBackwards()
    {
        if(tail != null)
            tail = tail.getPrev();//move to previous node
    }

    public void addFirst(E e)
    {
        if (size == 0)
        {
            tail = new Node<>(e, null, null);
            tail.setPrev(tail);//set prev reference
            tail.setNext(tail);
        }
        else
        {
            Node<E> newest = new Node<>(e, tail, tail.getNext());//new node's prev is the tail and it's next is the head before insertion
            Node<E> head = tail.getNext();//create head pointer
            tail.setNext(newest);//set tail next to newest node
            head.setPrev(newest);//set head prev to newest node
        }
        size++;
    }

    public void addLast(E e)
    {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst()
    {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else
            {
                tail.setNext(head.getNext());
                head.getNext().setPrev(tail);
            }
        size--;
        return head.getElement();
    }
    public E removeLast()// add remove last method
    {
        if(isEmpty()) return null;//if no node in the list return null
        Node<E> end = tail.getPrev();
        E element = tail.getElement();//store current tail element
        if(end == tail) tail = null;//if only one element then set tail to null
        else
        {
            tail.getNext().setPrev(end);//set head prev to the new tail node
            end.setNext(tail.getNext());//set new tail next to head node
            tail = end;//set the tail pointing to the new tail
        }
        return element;//return the removed element
    }

    public String toString()
    {
        if (tail == null) return "()";
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = tail;
        do
        {
            walk = walk.getNext();
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
        }
        while (walk != tail);
        sb.append(")");
        return sb.toString();
    }
}
