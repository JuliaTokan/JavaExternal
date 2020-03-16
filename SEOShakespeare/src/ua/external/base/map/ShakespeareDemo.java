package ua.external.base.map;

import ua.external.base.map.controller.SearchController;
import ua.external.base.map.controller.StatisticController;
import ua.external.base.map.model.Statistic;
import ua.external.base.map.view.StatisticView;

public class ShakespeareDemo {
    public static void main(String[] args) throws Exception {
        Statistic statistic = new Statistic();
        StatisticView statisticView = new StatisticView();
        StatisticController statisticController = new StatisticController(statistic, statisticView);
        SearchController searchController = new SearchController(statistic, statisticView);

        statisticController.createStatistic();
        searchController.searchWord();
    }
}
