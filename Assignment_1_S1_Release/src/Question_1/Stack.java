/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.util.NoSuchElementException;

/**
 *
 * @author xhu
 */
public class Stack <E extends Comparable>{
    public int size = 0;
    LinkedList<E> stack = new LinkedList();
    
    public void push(E data)
    {
       stack.addHead(data);
    }
    
    public E pop()
    {
        if(stack.isEmpty()){
            return null;
        }
        return (E) stack.removeFromHead().data;
    }
    
    public void printStack()
    {
        stack.printLinkedList();
    }
    
    public int getSize()
    {
        return stack.size;
    }

}
