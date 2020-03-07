package com.zipcodewilmington.looplabs;

public class Node<K, V> {
    private K key;
    private V value;
    private Node next;
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public V getValue() { return value; }
    public K getKey() { return key; }
    public Boolean hasNext() { return next != null; }
    public Node<K,V> getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
    public void setValue(V value) { this.value = value; }
}

