package log.analysis.linux;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LinuxAuthFailureStatisticService {
    /**
     * Получение статистики сколько было неудачных попыток с каждого хоста по дням
     *
     * @param records записи о неудачных попытках входа
     * @return статистика (сколько было неудачных попыток с каждого хоста по дням)
     */
    public Map<LocalDate, Map<String, Integer>> groupByDateAndHostname(List<LinuxAuthFailureRecord> records) {
        //чтоб была сортировка по дате немного пожертвуем скоростью, сделаем дерево, а не хэш-таблицу
        Map<LocalDate, Map<String, Integer>> result = new TreeMap<>();
        for (LinuxAuthFailureRecord record : records) {
            String rhost = record.rhost() != null && !record.rhost().isBlank() ? record.rhost() : "unknown";
            if (result.containsKey(record.date())) {
                Map<String, Integer> hostnameAndCnt = result.get(record.date());
                if (hostnameAndCnt.containsKey(rhost)) {
                    hostnameAndCnt.put(rhost, hostnameAndCnt.get(rhost) + 1);
                } else {
                    hostnameAndCnt.put(rhost, 1);
                }
            } else {
                result.put(record.date(), new TreeMap<>(Map.of(rhost, 1))); //по хосту тоже сортировка
            }
        }
        return result;
    }
}
