package demo.service;

import demo.models.LogEntry;
import demo.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggingService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public void log(String level, String logger, String message, String exception) {
        LogEntry logEntry = new LogEntry();
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setLevel(level);
        logEntry.setLogger(logger);
        logEntry.setMessage(message);
        logEntry.setException(exception);

        logEntryRepository.save(logEntry);
    }

    public Page<LogEntry> findAllLogs(Pageable pageable) {
        return logEntryRepository.findAll(pageable);
    }
}
