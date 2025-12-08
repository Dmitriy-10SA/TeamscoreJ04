package log.analysis.linux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для получения статистики неудачного входа
 */
public class LinuxAuthFailureParser {
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String HOSTNAME = "hostname";
    private static final String SERVICE = "service";
    private static final String MODULE = "module";
    private static final String PID = "pid";
    private static final String LOGNAME = "logname";
    private static final String UID = "uid";
    private static final String EUID = "euid";
    private static final String TTY = "tty";
    private static final String RUSER = "ruser";
    private static final String RHOST = "rhost";
    private static final String USER = "user";

    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MMM d")
            .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
            .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Regex для поиска неудачных попыток входа и получения статистики по группам
     */
    private static final Pattern AUTH_FAILURE_PATTERN = Pattern.compile(
            "^(?<" + DATE + ">[a-zA-Z]+\\s+\\d{1,2})\\s+" +
                    "(?<" + TIME + ">\\d{2}:\\d{2}:\\d{2})\\s+" +
                    "(?<" + HOSTNAME + ">\\S+)\\s+" +
                    "(?<" + SERVICE + ">\\w+)\\(" +
                    "(?<" + MODULE + ">\\w+)\\)\\[" +
                    "(?<" + PID + ">\\d+)]:\\s+" +
                    "authentication\\s+failure;\\s+" +
                    "logname=(?<" + LOGNAME + ">\\S*)\\s+" +
                    "uid=(?<" + UID + ">\\d*)\\s+" +
                    "euid=(?<" + EUID + ">\\d*)\\s+" +
                    "tty=(?<" + TTY + ">\\S*)\\s+" +
                    "ruser=(?<" + RUSER + ">\\S*)\\s+" +
                    "rhost=(?<" + RHOST + ">\\S*)\\s*" +
                    "(user=(?<" + USER + ">\\S*)\\s*)?$"
    );

    /**
     * Получение даты по строке с датой в формате MMM d
     *
     * @param dateString строка с датой в формате MMM d
     * @return дата типа LocalDate
     */
    private static LocalDate getLocalDateByDateString(String dateString) {
        String formattedDateString = dateString.trim().replaceAll("\\s+", " ");
        String[] parts = formattedDateString.split(" ");
        if (parts[1].equals("00")) {
            return LocalDate.parse(parts[0] + " " + "01", DATE_FORMATTER).minusDays(1);
        }
        return LocalDate.parse(formattedDateString, DATE_FORMATTER);
    }

    /**
     * Получение записей о неудачном входа
     *
     * @return записи о неудачном входе
     */
    public static List<LinuxAuthFailureRecord> parse(String logFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(logFilePath));
        return lines.stream()
                .map(AUTH_FAILURE_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new LinuxAuthFailureRecord(
                        getLocalDateByDateString(matcher.group(DATE)),
                        LocalTime.parse(matcher.group(TIME), TIME_FORMATTER),
                        matcher.group(HOSTNAME),
                        matcher.group(SERVICE),
                        matcher.group(MODULE),
                        Integer.parseInt(matcher.group(PID)),
                        matcher.group(LOGNAME),
                        Integer.parseInt(matcher.group(UID)),
                        Integer.parseInt(matcher.group(EUID)),
                        matcher.group(TTY),
                        matcher.group(RUSER),
                        matcher.group(RHOST),
                        matcher.group(USER)
                ))
                .toList();
    }
}
