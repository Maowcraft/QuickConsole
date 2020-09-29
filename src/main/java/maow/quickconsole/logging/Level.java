package maow.quickconsole.logging;

import maow.quickconsole.util.ConsoleColors;

public enum Level {
    INFO(ConsoleColors.WHITE, ""),
    WARN(ConsoleColors.YELLOW, ""),
    ERROR(ConsoleColors.RED, ""),
    FATAL(ConsoleColors.WHITE, ConsoleColors.RED_BACKGROUND)
    ;

    public String foreground;
    public String background;

    Level(String foreground, String background) {
        this.foreground = foreground;
        this.background = background;
    }
}
