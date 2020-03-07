package com.zipcodewilmington.looplabs;

import java.util.Arrays;

public class Indexer<K, V extends Integer> {

    private Node[] nodes;

    public Indexer(Integer maxCapacity) {
        nodes = new Node[maxCapacity];
    }

    public void resize(Integer newCapacity) {
        nodes = Arrays.copyOf(nodes, newCapacity + 1);
    }

    public Integer hash(K input) {
        if (input instanceof Integer) { return (Integer) input; }
        else if (input.toString().length() > 0) {
            try { return (Integer.parseInt((String) input)); }
            catch (NumberFormatException ex) { return input.toString().charAt(0) - 'a'; }
        }
        return 0;
    }

    public void put(K key, V value) {
        Integer index = hash(key);
        if (index >= nodes.length) { resize(index + 1); }
        nodes[index] = new Node(key, value);
    }

    public void set(K key, V value) {
        Integer index = hash(key);
        Node<K, V> newNode = new Node(key, value);
        if (index >= nodes.length) { resize(index + 1); }
        Node<K, V> head = nodes[index];
        if (head == null) {
            nodes[index] = newNode;
        } else {
            while (head.hasNext() || (head != null && head.getKey().equals(key))) {
                if (head.getKey().equals(key)) {
                    head.setValue(value);
                    return;
                }
                head = head.getNext();
            }
            head.setNext(newNode);
        }
    }

    public V get(K key) {
        Integer index = hash(key);
        if (index >= nodes.length) { return null; }
        Node<K, V> head = nodes[index];
        if (head != null && head.getValue() != null) {
            if (head.getKey().equals(key)) {
                return head.getValue();
            } else {
                while (head != null && head.hasNext()) {
                    if (head.getNext().getKey().equals(key)) {
                        return head.getNext().getValue();
                    }
                    head = head.getNext();
                }
            }
        }
        return null;
    }

    public Node[] getList() {
        return nodes;
    }

    public Boolean contains(K key) {
        return get(key) != null;
    }

}