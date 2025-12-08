package log.analysis.apache;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String LOG_FILE_PATH = "src/main/java/log/analysis/apache/apache_log_file";

    public static void main(String[] args) {
        try {
            List<ApacheErrorRecord> records = ApacheErrorParser.parse(LOG_FILE_PATH);
            records.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Упс! Что-то пошло не так: " + e);
        }
    }
}
