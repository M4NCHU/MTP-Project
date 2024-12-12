package demo.models.sqlserver;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_entry") // Określenie nazwy tabeli w bazie danych
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Użycie strategii odpowiedniej dla SQL Server
    private Long id;

    @Column(nullable = false) // Pole wymagane
    private LocalDateTime timestamp;

    @Column(length = 50)
    private String level;

    @Column(length = 100)
    private String logger;

    @Column(length = 255)
    private String message;

    @Column(length = 500)
    private String exception;

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
