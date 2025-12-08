package log.analysis.linux;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String LOG_FILE_PATH = "src/main/java/log/analysis/linux/linux_log_file";

    private static List<LinuxAuthFailureRecord> getRecords() throws IOException {
        return LinuxAuthFailureParser.parse(LOG_FILE_PATH);
    }

    private static Map<LocalDate, Map<String, Integer>> getStatistic(List<LinuxAuthFailureRecord> records) {
        LinuxAuthFailureStatisticService statisticService = new LinuxAuthFailureStatisticService();
        return statisticService.groupByDateAndHostname(records);
    }

    private static void printStatistic(Map<LocalDate, Map<String, Integer>> statistic) {
        for (Map.Entry<LocalDate, Map<String, Integer>> entry : statistic.entrySet()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Дата: " + entry.getKey());
            entry.getValue().forEach((key, value) ->
                    System.out.println("Хост: " + key + ", неудачных попыток: " + value)
            );
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void main(String[] args) {
        try {
            List<LinuxAuthFailureRecord> records = getRecords();
            Map<LocalDate, Map<String, Integer>> statistic = getStatistic(records);
            printStatistic(statistic);
        } catch (IOException e) {
            System.out.println("Упс! Что-то пошло не так: " + e);
        }
    }
}
