/* *********************************************************************
* File:       DSAQueue.java
* Author:     G.G.T.Shashen
* Created:    01/09/2021
* Modified:   19/10/2021
* Desc:       DSAQueue implementation for dfs. Taken from practical 6
************************************************************************/
import java.io.*;
import java.util.*;
/**
 * DSAQueue
 */
public class DSAQueue implements Iterable, Serializable {
    private DSALinkedList list;
    public int first;
    public int last;
    
    public DSAQueue() {
        list = new DSALinkedList();
    }

    public boolean isEmpty() {
        boolean empty = false;
        if (list.isEmpty()) {
            empty = true;
        }
        return empty;
    }

    public void enqueue(Object element) {
        list.insertLast(element);
    }

    public Object deQueue() {
        Object element;
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue Empty");
        }
        else {
            element = list.peekFirst();
            list.removeFirst();
            return element;
        }
    }

    public Object peek() {
        Object element;
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack Empty");
        }
        else {
            element = list.peekFirst();
        }
        return element;
    }

    public Iterator iterator() {
        return list.iterator();
    }
}