package demo.controller;

import demo.models.sqlserver.LogEntry;
import demo.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping
    public ResponseEntity<Page<LogEntry>> getAllLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<LogEntry> logs = loggingService.findAllLogs(PageRequest.of(page, size));
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
