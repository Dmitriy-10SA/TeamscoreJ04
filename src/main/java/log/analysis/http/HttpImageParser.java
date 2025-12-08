package log.analysis.http;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpImageParser {
    private static final String HOST = "host";
    private static final String PATH = "path";

    private static final Pattern IMAGE_PATTERN = Pattern.compile(
            "^(?<" + HOST + ">\\S+)\\s+-\\s+-\\s+\\[[^]]+]\\s+" +
                    "\"GET\\s+(?<" + PATH + ">\\S*?/images/\\S*)\\s+HTTP/\\S+\"\\s+200\\s+\\d+.*$"
    );

    /**
     * Нахождение всех успешных GET-запросов изображений и получение списка доменного имени и пути к файлу
     *
     * @param logFilePath путь к лог-файлу
     * @return список доменных имен и путей к файлам
     */
    public static List<ImageRecord> parse(String logFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(logFilePath));
        return lines.stream()
                .map(IMAGE_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(m -> new ImageRecord(
                        m.group(HOST),
                        m.group(PATH)
                ))
                .toList();
    }
}
