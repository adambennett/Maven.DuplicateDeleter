package com.zipcodewilmington.looplabs;

import java.util.Arrays;

/**
 * Created by leon on 1/29/18.
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class IntegerDuplicateDeleter extends DuplicateDeleter<Integer> {

    public IntegerDuplicateDeleter(Integer[] intArray) {
        super(intArray);
    }

    @Override
    public Integer[] removeDuplicates(int maxNumberOfDuplications) {
        return getDeletedArray(maxNumberOfDuplications, false);
    }

    @Override
    public Integer[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        return getDeletedArray(exactNumberOfDuplications, true);
    }

    public Indexer<Integer, Integer> makeMap() {
        Indexer<Integer, Integer> map = new Indexer<>(this.array.length);
        for (Integer entry : this.array) {
            if (map.contains(entry)) {
                map.put(entry, map.get(entry) + 1);
            } else {
                map.put(entry, 1);
            }
        }
        return map;
    }


    public Integer[] addValuesToArray(Integer[] oldArr, int valueToAdd, int amtToAdd){
        Integer[] newArr = Arrays.copyOf(oldArr, oldArr.length + amtToAdd);
        for (int i = oldArr.length; i < newArr.length; i++) {
            newArr[i] = valueToAdd;
        }
        return newArr;
    }

    public Integer[] getDeletedArray(int dupes, boolean exact) {
        Indexer<Integer, Integer> map = makeMap();
        Integer[] toRet = new Integer[0];
        Node[] nodes = map.getList();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) { continue; }
            Integer occurrences = (Integer) nodes[i].getValue();
            if (occurrences == dupes && exact) {
                continue;
            }
            else if (occurrences >= dupes && !exact) {
                continue;
            }
            Integer value = (Integer) nodes[i].getKey();
            toRet = addValuesToArray(toRet, value, occurrences);
        }
        return toRet;
    }

}
