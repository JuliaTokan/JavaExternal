package ua.external.base.map.controller;

import ua.external.base.map.model.Statistic;
import ua.external.base.map.view.StatisticView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class SearchController {
    private Statistic statistic;
    private StatisticView statisticView;

    public SearchController(Statistic statistic, StatisticView statisticView) {
        this.statistic = statistic;
        this.statisticView = statisticView;
    }

    public void searchWord() {
        statisticView.printMenu();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String word = bufferedReader.readLine();
            Map<String, Integer> result = search(word);

            if (result.size() == 0)
                statisticView.printInfo("Word not found");
            else
                statisticView.printInfoUrl(result);

        } catch (IOException e) {
            statisticView.printInfo("Incorrect word");
        }
    }

    private Map<String, Integer> search(String word) {
        Map<String, Map<String, Integer>> statisticWords = statistic.getWordsStatistic();

        Map<String, Integer> result = statisticWords
                .entrySet()
                .stream()
                .filter(x -> x.getKey().equals(word))
                .findFirst()
                .get().getValue();

        return sort(result);
    }

    private LinkedHashMap<String, Integer> sort(Map<String, Integer> urlInfo){
        return urlInfo
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}
