import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {

    @Option(name = "-r", usage = "Search with regex")
    private boolean regex;

    @Option(name = "-i", usage = "IgnoreCase")
    private boolean iCase;

    @Option(name = "-v", usage = "reverse search")
    private boolean reverse;

    @Argument(required = true, metaVar = "Word", usage = "input word or regex")
    private String inputWord;

    @Argument(required = true, metaVar = "FileName", index = 1, usage = "input filr name")
    private String inputFileName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] arg) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(arg);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -i -v -r Word inputName.txt");
            parser.printUsage(System.err);
            return;
        }
        Grep grep = new Grep(inputWord, inputFileName);

        if (iCase) {
            grep.setCommandI(true);
        }
        if (reverse) {
            grep.setCommandV(true);
        }
        if (regex) {
            grep.setCommandR(true);
        }

        try {
            for (String line : grep.findContent()) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
