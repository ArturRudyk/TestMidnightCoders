package com.arudyk;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SorterOfStrings {

    List<String> unsortedList;

    SorterOfStrings(List<String> unsortedList) {
        this.unsortedList = unsortedList;
    }

    public void displayResults() {
        System.out.println(getSumOfWords());
        System.out.println(sort());
    }

    private Map<String, Integer> sort() {
        Map<String,Integer> sortedMap = new TreeMap<String,Integer>();
        Iterator iterator = unsortedList.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Integer count;
            if (sortedMap.containsKey(key)) {
                count = sortedMap.get(key) + 1;
            }else {
                count = 1;
            }
            sortedMap.put(key, count);
        }
        return sortedMap;
    }

    private int getSumOfWords() {
        return unsortedList.size();
    }
}