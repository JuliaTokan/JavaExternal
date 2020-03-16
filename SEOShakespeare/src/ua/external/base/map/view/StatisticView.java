package ua.external.base.map.view;

import java.util.Map;

public class StatisticView {
    public void printRes(Map<String, Map<String, Integer>> sortedWordsStatistic) {
        for (String word : sortedWordsStatistic.keySet()) {
            System.out.println(word);
            Map<String, Integer> info = sortedWordsStatistic.get(word);
            for (String url : info.keySet()) {
                System.out.println("     " + url + " : " + info.get(url));
            }
        }
    }

    public void printInfoUrl(Map<String, Integer> urlInfo){
        for(String url:urlInfo.keySet()){
            System.out.println(url+" : "+urlInfo.get(url));
        }
    }

    public void printInfo(String info){
        System.out.println(info);
    }

    public void printMenu(){
        System.out.println("Enter word:");
    }
}
