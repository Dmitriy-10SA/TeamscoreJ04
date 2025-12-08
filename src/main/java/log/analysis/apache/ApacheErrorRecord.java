package log.analysis.apache;

import java.time.LocalDateTime;

public record ApacheErrorRecord(LocalDateTime dateTime, String ip, String message) {
}
