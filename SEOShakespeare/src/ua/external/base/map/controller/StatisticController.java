package ua.external.base.map.controller;

import ua.external.base.map.ReaderURL;
import ua.external.base.map.RomanNumeral;
import ua.external.base.map.model.Statistic;
import ua.external.base.map.view.StatisticView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class StatisticController {
    private static final int MAX_PAGE = 20;

    private Statistic statistic;
    private StatisticView statisticView;

    public StatisticController(Statistic statistic, StatisticView statisticView) {
        this.statistic = statistic;
        this.statisticView = statisticView;
    }

    public void createStatistic() throws Exception {
        for (int i = 1; i <= MAX_PAGE; i++) {
            String num = RomanNumeral.getRomanNumeral(i);
            String url = "http://shakespeare.mit.edu/Poetry/sonnet." + num + ".html";
            String text = ReaderURL.getTextWithoutHtml(url);

            Map<String, Integer> counter = count(text);

            doStatistics(counter, url);
        }

        Map<String, Map<String, Integer>> sortUrlWordsStatistic = sortUrl(statistic.getWordsStatistic());
        Map<String, Map<String, Integer>> sortWordsStatistic = sortWord(sortUrlWordsStatistic);

        statisticView.printRes(sortWordsStatistic);
    }

    public void doStatistics(Map<String, Integer> counter, String url) {
        for (String word : counter.keySet()) {
            Map<String, Integer> urlInfo = statistic.getWordsStatistic().get(word);
            if (urlInfo == null) {
                urlInfo = new HashMap<>();
            }
            urlInfo.put(url, counter.get(word));
            statistic.add(word, urlInfo);
        }
    }

    public Map<String, Integer> count(String str) {
        Map<String, Integer> counter = new HashMap<>();

        String[] words = str.split(" ");

        for (String a : words) {
            Integer freq = counter.get(a);
            counter.put(a, (freq == null) ? 1 : freq + 1);
        }

        return counter;
    }

    public static Map<String, Map<String, Integer>> sortUrl(Map<String, Map<String, Integer>> statistic) {
        Map<String, Map<String, Integer>> sortUrlStatistic = new HashMap<>();

        for (String word : statistic.keySet()) {
            Map<String, Integer> urlInfo = statistic.get(word)
                    .entrySet()
                    .stream()
                    .sorted(comparingByValue())
                    .collect(
                            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                    LinkedHashMap::new));

            sortUrlStatistic.put(word, urlInfo);
        }
        return sortUrlStatistic;
    }

    public static Map<String, Map<String, Integer>> sortWord(Map<String, Map<String, Integer>> statistic) {
        return statistic
                .entrySet()
                .stream()
                .sorted((x1, x2) -> x2.getValue().values().stream()
                        .reduce(0, Integer::sum) - x1.getValue().values().stream()
                        .reduce(0, Integer::sum))
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}
