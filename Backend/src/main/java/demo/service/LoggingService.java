package demo.service;

import demo.models.sqlserver.LogEntry;
import demo.repository.sqlserver.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service class for managing log entries.
 */
@Service
public class LoggingService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    /**
     * Logs an event with the specified details.
     *
     * @param level     the log level (e.g., INFO, ERROR).
     * @param logger    the name of the logger.
     * @param message   the log message.
     * @param exception the exception message, if any.
     */
    public void log(String level, String logger, String message, String exception) {
        LogEntry logEntry = new LogEntry();
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setLevel(level);
        logEntry.setLogger(logger);
        logEntry.setMessage(message);
        logEntry.setException(exception);

        logEntryRepository.save(logEntry);
    }

    /**
     * Retrieves all log entries with pagination.
     *
     * @param pageable the pagination information.
     * @return a {@link Page} of log entries.
     */
    public Page<LogEntry> findAllLogs(Pageable pageable) {
        return logEntryRepository.findAll(pageable);
    }
}
