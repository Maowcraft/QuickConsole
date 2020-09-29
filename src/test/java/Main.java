import maow.quickconsole.logging.Level;
import maow.quickconsole.logging.Logger;
import maow.quickconsole.util.ParametricRunnable;
import maow.quickconsole.console.Console;

public class Main extends Console {
    public static void main(String[] args) {
        // Args: -t="value to print to console"
        Logger logger = new Logger("Test Logger");
        addArgument("t", "Enables test print.", new ParametricRunnable<String>() {
            @Override
            public void onRun() {
                if (this.getValue().isPresent()) {
                    logger.log(Level.INFO, this.getValue().get());
                }
            }
        });
        processArgs(args);
        logger.writeFile();
        System.out.println("\n");
        ParametricRunnable<String> runnable = ParametricRunnable.of(() -> System.out.println("String that is printed on ParametricRunnable start."), "Default value for ParametricRunnable.");
        runnable.run();
        runnable.getValue().ifPresent(System.out::println);
        runnable.setValue("New ParametricRunnable value.");
        runnable.getValue().ifPresent(System.out::println);
    }
}
