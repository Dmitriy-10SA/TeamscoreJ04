package log.analysis.http;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String LOG_FILE_PATH = "src/main/java/log/analysis/http/http_log_file";

    public static void main(String[] args) {
        try {
            List<ImageRecord> records = HttpImageParser.parse(LOG_FILE_PATH);
            records.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Упс! Что-то пошло не так: " + e);
        }
    }
}
