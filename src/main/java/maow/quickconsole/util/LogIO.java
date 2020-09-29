package maow.quickconsole.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogIO {
    public static void writeFile(List<String> log) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(
                            new File("log-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")) + ".txt")
                    )
            );
            for (String string : log) {
                writer.write(string);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
