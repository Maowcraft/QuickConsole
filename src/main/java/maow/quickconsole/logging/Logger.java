package maow.quickconsole.logging;

import maow.quickconsole.util.ConsoleColors;
import maow.quickconsole.util.LogIO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final String loggerName;
    private final String format;
    private final List<String> log = new ArrayList<>();

    public Logger(String loggerName, String format) {
        this.loggerName = loggerName;
        this.format = format;
    }

    public Logger(String loggerName) {
        this.loggerName = loggerName;
        this.format = "%s | (%s) [%s] %s";
    }

    public Logger() {
        this.loggerName = "Log";
        this.format = "%s | (%s) [%s] %s";
    }

    /**
     * Log message to chat, severity is based on level.
     * @param level - Type of message.
     * @param message - The message itself.
     */
    public void log(Level level, String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(String.format(format, time, loggerName, level.toString(), (level.foreground + level.background + message)) + ConsoleColors.RESET);
        log.add(String.format(format, time, loggerName, level.toString(), message));
    }

    /**
     * Write the Logger's current log to a file in the run dir.
     */
    public void writeFile() {
        LogIO.writeFile(log);
    }
}
