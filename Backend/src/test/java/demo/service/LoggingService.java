package demo.service;

import demo.models.LogEntry;
import demo.repository.LogEntryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoggingServiceTest {

    @Mock
    private LogEntryRepository logEntryRepository;

    @InjectMocks
    private LoggingService loggingService;

    public LoggingServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLog() {
        // Arrange
        LogEntry logEntry = new LogEntry();
        logEntry.setLevel("INFO");
        logEntry.setLogger("TestLogger");
        logEntry.setMessage("Test message");
        logEntry.setException(null);

        // Act
        loggingService.log("INFO", "TestLogger", "Test message", null);

        // Assert
        verify(logEntryRepository, times(1)).save(any(LogEntry.class));
    }

    @Test
    void testFindAllLogs() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<LogEntry> logEntries = Collections.singletonList(new LogEntry());
        Page<LogEntry> page = new PageImpl<>(logEntries, pageable, logEntries.size());
        when(logEntryRepository.findAll(pageable)).thenReturn(page);

        // Act
        Page<LogEntry> result = loggingService.findAllLogs(pageable);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(logEntryRepository, times(1)).findAll(pageable);
    }
}
