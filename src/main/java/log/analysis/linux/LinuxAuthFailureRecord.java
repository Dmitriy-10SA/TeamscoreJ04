package log.analysis.linux;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Запись о неудачном входе
 */
public record LinuxAuthFailureRecord(
        LocalDate date,
        LocalTime time,
        String hostname,
        String service,
        String module,
        int pid,
        String logname,
        int uid,
        int euid,
        String tty,
        String ruser,
        String rhost,
        String user
) {
}
