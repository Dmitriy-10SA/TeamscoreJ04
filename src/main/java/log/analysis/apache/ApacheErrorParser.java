package log.analysis.apache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheErrorParser {
    private static final String DATETIME = "datetime";
    private static final String IP = "ip";
    private static final String MESSAGE = "message";

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(
            "EEE MMM dd HH:mm:ss yyyy",
            Locale.ENGLISH
    );

    private static final Pattern ERROR_PATTERN = Pattern.compile(
            "^\\[(?<" + DATETIME + ">[^]]+)]\\s+" +
                    "\\[error](?!.*mod_jk)\\s*" +
                    "(?:\\[client\\s+(?<" + IP + ">\\d{1,3}(?:\\.\\d{1,3}){3})])?\\s*" +
                    "(?<" + MESSAGE + ">.*)$"
    );

    /**
     * Получение всех ошибок, которые не касаются mod_jk
     *
     * @param logFilePath путь к файлу с логами
     * @return список ошибок
     */
    public static List<ApacheErrorRecord> parse(String logFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(logFilePath));
        return lines.stream()
                .map(ERROR_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new ApacheErrorRecord(
                        LocalDateTime.parse(matcher.group(DATETIME), DATETIME_FORMATTER),
                        matcher.group(IP),
                        matcher.group(MESSAGE)
                ))
                .toList();
    }
}
