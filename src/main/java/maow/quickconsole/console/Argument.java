package maow.quickconsole.console;

import maow.quickconsole.util.ParametricRunnable;

/**
 * Console application argument.
 */
public class Argument {
    private final String identifier;
    private final String description;
    private final ParametricRunnable<String> onProcessed;

    public Argument(String identifier, String description, ParametricRunnable<String> onProcessed) {
        this.identifier = identifier;
        this.description = description;
        this.onProcessed = onProcessed;
    }

    public String getIdentifier() {
        return identifier;
    }
    public String getDescription() {
        return description;
    }
    public ParametricRunnable<String> getRunnable() {
        return onProcessed;
    }
}
