package maow.quickconsole.console;

import maow.quickconsole.util.ParametricRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Console {
    private static final Map<String, Argument> validArgs = new HashMap<>();

    /**
     * Adds an argument to the list of args your console app can process.
     * @param argument - The argument you want to add.
     */
    protected static void addArgument(Argument argument) {
        if (!validArgs.containsKey(argument.getIdentifier())) {
            validArgs.put(argument.getIdentifier(), argument);
        } else {
            System.err.println("Identifier " + argument.getIdentifier() + " already added to console's args list.");
        }
    }

    /**
     * Adds an argument to the list of args your console app can process.
     * @param identifier - The identifier of the argument, usually a single/sequence of letters.
     * @param description - The description of the argument.
     * @param onProcessed - What this argument does when it is processed.
     */
    protected static void addArgument(String identifier, String description, ParametricRunnable<String> onProcessed) {
        addArgument(new Argument(identifier, description, onProcessed));
    }

    /**
     * Adds an argument to the list of args your console app can process.
     * @param identifier - The identifier of the argument, usually a single/sequence of letters.
     * @param onProcessed - What this argument does when it is processed.
     */
    protected static void addArgument(String identifier, ParametricRunnable<String> onProcessed) {
        addArgument(new Argument(identifier, "", onProcessed));
    }

    /**
     * Process args given by a command-line interface.
     * @param args - Command-line args.
     */
    protected static void processArgs(String[] args) {
        List<String> processedIdentifiers = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                boolean containsValue = false;
                if (arg.contains("=")) {
                    containsValue = true;
                }
                String value = "";
                String identifier = arg.substring(1);
                if (containsValue) {
                    value = arg.substring(arg.indexOf('=') + 1);
                    identifier = arg.substring(1, arg.indexOf('='));
                }
                Argument argument = validArgs.get(identifier);
                if (argument != null && !processedIdentifiers.contains(identifier)) {
                    ParametricRunnable<String> parametricRunnable = argument.getRunnable();
                    parametricRunnable.setValue(value);
                    parametricRunnable.run();
                    parametricRunnable.close();
                    processedIdentifiers.add(identifier);
                }
            }
        }
    }
}
