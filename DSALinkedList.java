/* *********************************************************************
* File:       DSAGraph.java
* Author:     G.G.T.Shashen
* Created:    01/09/2021
* Modified:   17/10/2021
* Desc:       DSALinkedList implementation from Practical 6
************************************************************************/
import java.io.*;
import java.util.*;

public class DSALinkedList implements Serializable, Iterable {

    private class DSAListNode implements Serializable
    {
        private Object m_value;
        private DSAListNode m_next;
        private DSAListNode m_prev;
        public DSAListNode(Object inValue)
        {
            m_value = inValue;
            m_next = null;
            m_prev = null;
        }

        public Object getValue()
        {
            return m_value;
        }

        public void setNext(DSAListNode next)
        {
            this.m_next = next;
        }

        public DSAListNode getNext()
        {
            return m_next;
        }

        public void setPrev(DSAListNode prev)
        {
            this.m_prev = prev;
        }

        public DSAListNode getPrev()
        {
            return m_prev;
        }
    }

    DSAListNode head;
    DSAListNode tail;

    DSALinkedList() {
        head = null;
        tail = null;
    }

    public DSAListNode find(Object value) {
        DSAListNode node = recFind(head, value);
        return node;
    }

    private DSAListNode recFind(DSAListNode node, Object value) {
        DSAListNode found;
        if(node == null) {
            found = null;
        }
        else if(node.getValue() == value) {                                
            found = node;
        }
        else {
            found = recFind(node.getNext(), value);
        }                                          
        return found;
    }

    public void insertFirst(Object newValue) {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        }else {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
    }

    public void insertLast(Object newValue) {
        DSAListNode newNd;
        DSAListNode currNd;
        newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        }else {
            currNd = head;
            while (currNd.getNext() != null) {
                currNd = currNd.getNext();
            }
            newNd.setPrev(tail);
            currNd.setNext(newNd);
            tail = newNd;
        }
    }

    public boolean isEmpty() {
        boolean empty;
        empty = false;
        if (head == null) {
            empty = true;
        }
        return empty;
    }

    public Object peekFirst() {
        Object nodeValue;
        if (isEmpty()) {
            throw new IllegalArgumentException("List is Empty");
        }else {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    public Object peekLast() {
        Object nodeValue;
        DSAListNode currNd;
        if (isEmpty()) {
            throw new IllegalArgumentException("List is Empty");
        }else  {
            currNd = head;
            while (currNd.getNext() != null) {
                currNd = currNd.getNext();
            }
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    public Object removeFirst() {
        Object nodeValue;
        if (isEmpty()) {
            throw new IllegalArgumentException("List is Empty");
        }else {
            nodeValue = head.getValue();
            head = head.getNext();
        }
        return nodeValue;
    }

    public Object removeLast() {
        Object nodeValue;
        if (isEmpty()) {
            throw new IllegalArgumentException("List is Empty");
        }else if (head.getNext() == null ) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }else {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.getNext().setPrev(null);
            tail.setNext(null);
        }
        return nodeValue;
    }

    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    private class DSALinkedListIterator implements Iterator, Serializable
    {
        private DSAListNode iterNext;

        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return iterNext != null;
        }

        public Object next()
        {
            Object value;
            if(iterNext == null) {
                value = null;
            }else{
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }

    }
}