import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IpTest {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Not enough arguments");
            System.exit(-1);
        }

        String originalInputFile = args[0];

        String inputFile = args[0];
        String outputFile = Paths.get(inputFile).getParent() + "/out.txt";

        boolean moreWork = false;

        int count = 0;

        do {
            moreWork = false;

            FileInputStream inputStream = new FileInputStream(inputFile);
            Scanner sc = new Scanner(inputStream, "UTF-8");

            PrintWriter pw = new PrintWriter(new FileWriter(outputFile));

            String checkStr = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.charAt(line.length() - 1) == '!') {
                    pw.write(line + System.lineSeparator());
                    continue;
                } else if (line.charAt(line.length() - 1) == 'x') {
                    pw.write(line + System.lineSeparator());
                    continue;
                } else {
                    moreWork = true;
                    if (checkStr == null) {
                        pw.write(line + "!" + System.lineSeparator());
                        checkStr = line;
                    } else {
                        if (checkStr.equals(line)) {
                            pw.write(line + "x" + System.lineSeparator());
                        } else {
                            pw.write(line + System.lineSeparator());
                        }
                    }
                }
            }

            inputStream.close();
            sc.close();
            pw.close();

            if (moreWork) {
                Files.deleteIfExists(Paths.get(inputFile));
                inputFile = outputFile;

                if (outputFile.contains("!_1_!")) {
                    outputFile = outputFile.split("!_1_!")[0] + "!_1_!" + String.valueOf(count);
                } else {
                    outputFile = outputFile + "!_1_!" + String.valueOf(count);
                }

                count += 1;
            }
        } while (moreWork);

        Files.deleteIfExists(Paths.get(inputFile));

        FileInputStream inputStream = new FileInputStream(outputFile);
        Scanner sc = new Scanner(inputStream, "UTF-8");

        PrintWriter pw = new PrintWriter(new FileWriter(inputFile));

        int result = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.charAt(line.length() - 1) == '!'){
                result += 1;
            }
            pw.write(line.substring(0, line.length() - 1) + System.lineSeparator());
        }

        inputStream.close();
        sc.close();
        pw.close();

        Files.deleteIfExists(Paths.get(outputFile));
        Path source = Paths.get(inputFile);
        Files.move(source, source.resolveSibling(originalInputFile));

        System.out.println("There is " + String.valueOf(result) + " unique ip addresses in file " + originalInputFile);
    }
}
