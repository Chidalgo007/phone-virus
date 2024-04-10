/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

/**
 *
 * @author xhu
 */
public class LinkedList <E extends Comparable>{
    
    public int size = 0;
    public Node<E> head;
    
    public void addHead(E data)
    {
        Node<E> someNode = new Node<>();
        someNode.data = data;
        someNode.next = head;
        head = someNode;
        size++;
    }
    
    public Node getHead()
    {
        return head;
    }
    
    public void add(E data)
    {
        if(head == null){
            addHead(data);
            return;
        }
        Node<E> current = head;
        while (current.next != null)
        {
            current = current.next;
        }
        Node<E> someNode = new Node<>();
        someNode.data = data;
        current.next = someNode;
        size++;    
    }
    
    private void add(Node head, Node node)
    {

    }
    
    public void printLinkedList()
    {
        Node<E> current = head;
        while (current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("");
    }
    
    private void printLinkedList(Node node)
    {

    }
    
    public boolean contains(E data)
    {
        Node<E> current = head;
        while(current != null){
            if(current.data.equals(data)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    private boolean contains(Node head, Node node)
    {
        return false;
    }
    
    public void remove(E data)
    {
        if(head == null){
            return;
        }
        if(head.data.equals(data)){
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        while(current.next != null && !current.next.data.equals(data)){
            current = current.next;
        }
        if (current.next != null){
            current.next = current.next.next;
            size--;
        }
    }
    
    public void remove(int position)
    {
        if(position < 0 || position >= size || head == null){
            return;
        }
        if (position == 0){
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        for (int i=0;i<position - 1; i++){
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }
    
    private void removeByIndex(Node head, int position)
    {

    }
    
    private void removeFromBody(Node head, Node node)
    {

    }
    
    public Node removeFromHead()
    {
        if(head == null){
            return null;
        }
        Node<E> removedNode = head;
        head = head.next;
        size--;
        return removedNode;
    }
    
    public Node removeFromTail()
    {
        if(head == null || head.next == null){
            return removeFromHead();
        }
        Node<E> current = head;
        while (current.next.next != null){
            current = current.next;
        }
        Node<E> removedNode = current.next;
        current.next = null;
        size--;
        return removedNode;
    }
    
    private Node removeFromTail(Node node)
    {
        return null;
    }
    
    public void addInOrder(E data)
    {
        if(head == null || head.data.compareTo(data)>=0){
            addHead(data);
            return;
        }
        Node<E> current = head;
        while (current.next != null && current.next.data.compareTo(data)<0){
            current = current.next;
        }
        Node<E> someNode = new Node<>();
        someNode.data = data;
        someNode.next = current.next;
        current.next = someNode;
        size++;
    }
    
    private void addInOrder(Node currentNode, Node newNode)
    {

    }
    
    public Node getNode(int index)
    {
        if(index < 0 || index >= size || head == null){
            return null;
        }
        Node current = head;
        for(int i=0;i<index;i++){
            if(current == null){
                return null;
            }
            current = current.next;
        }
        return current;
    }
    
    private Node getNode(int index, Node head)
    {
        return null;
    }
    
    public E getData(int index)
    {
        if(index < 0 || index >= size || head == null){
            return null;
        }
        Node current = head;
        for (int i=0; i < index; i++){
            if(current == null){
                return null;
            }
            current = current.next;
        }
        return (E) current.data;
    }
    
    private E getData(int index, Node head)
    {
        
        return null;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
}
