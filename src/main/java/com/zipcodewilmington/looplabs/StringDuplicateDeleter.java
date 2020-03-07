package com.zipcodewilmington.looplabs;

import java.util.Arrays;

/**
 * Created by leon on 1/28/18.
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class StringDuplicateDeleter extends DuplicateDeleter<String> {
    public StringDuplicateDeleter(String[] intArray) {
        super(intArray);
    }

    @Override
    public String[] removeDuplicates(int maxNumberOfDuplications) {
        return getDeletedArray(maxNumberOfDuplications, false);
    }

    @Override
    public String[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        return getDeletedArray(exactNumberOfDuplications, true);
    }

    public Indexer<String, Integer> makeMap() {
        Indexer<String, Integer> map = new Indexer<>(26);
        for (String entry : this.array) {
            if (map.contains(entry)) {
                map.set(entry, map.get(entry) + 1);
            } else {
                map.set(entry, 1);
            }
        }
        return map;
    }


    public String[] addValuesToArray(String[] oldArr, String valueToAdd, int amtToAdd){
        String[] newArr = Arrays.copyOf(oldArr, oldArr.length + amtToAdd);
        for (int i = oldArr.length; i < newArr.length; i++) {
            newArr[i] = valueToAdd;
        }
        return newArr;
    }

    public String[] getDeletedArray(int dupes, boolean exact) {
        Indexer<String, Integer> map = makeMap();
        String[] toRet = new String[0];
        Node[] nodes = map.getList();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) { continue; }
            Node<String, Integer> curr = nodes[i];
            while (curr != null || (curr != null && curr.hasNext())) {
                Integer occurrences = curr.getValue();
                boolean skip = occurrences == dupes && exact;
                if (!skip) { skip = occurrences >= dupes && !exact; }
                if (!skip) {
                    String value = curr.getKey();
                    toRet = addValuesToArray(toRet, value, occurrences);
                }
                curr = curr.getNext();
            }
        }
        return toRet;
    }
}
