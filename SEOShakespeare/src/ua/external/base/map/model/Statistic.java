package ua.external.base.map.model;

import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private Map<String, Map<String, Integer>> wordsStatistic;

    public Statistic() {
        wordsStatistic = new HashMap<>();
    }

    public Map<String, Map<String, Integer>> getWordsStatistic() {
        return wordsStatistic;
    }

    public void setWordsStatistic(Map<String, Map<String, Integer>> wordsStatistic) {
        this.wordsStatistic = wordsStatistic;
    }

    public void add(String word, Map<String, Integer> urlStatistic){
        wordsStatistic.put(word , urlStatistic);
    }
}
