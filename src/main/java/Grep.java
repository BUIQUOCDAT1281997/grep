import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grep {

    private boolean commandR = false;
    private boolean commandI = false;
    private boolean commandV = false;

    private String wordOrRegex, pathName;

    public void setCommandR(boolean commandR) {
        this.commandR = commandR;
    }

    public void setCommandI(boolean commandI) {
        this.commandI = commandI;
    }

    public void setCommandV(boolean commandV) {
        this.commandV = commandV;
    }

    public List<String> findContent() throws IOException {

        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<String> result = new ArrayList<String>();
        String line = br.readLine();
        boolean status = false;

        while (line != null) {
            if (this.commandR) {
                if (this.commandI) {
                    status = line.toLowerCase().matches(wordOrRegex.toLowerCase());
                } else {
                    status = line.matches(wordOrRegex);
                }
            } else {
                if (this.commandI) {
                    status = StringUtils.containsIgnoreCase(line, wordOrRegex);
                } else {
                    status = line.contains(wordOrRegex);
                }
            }
            if (this.commandV) {
                status = !status;
            }
            if (status) {
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    public void correctCheck(String[] arguments) {
        List<String> cmdLine = Arrays.asList(arguments);
        if (cmdLine.size() < 2 || cmdLine.size() > 5) {
            throw new Error("Input structure is incorrect!");
        }
        if (cmdLine.size() > 2) {
            for (int i = 0; i < cmdLine.size() - 2; i++) {
                if (!cmdLine.get(i).equals("-i") && (!cmdLine.get(i).equals("-r") && !cmdLine.get(i).equals("-v"))) {
                    throw new Error("Input structure is incorrect!");
                }
            }
        }
        if (!StringUtils.endsWith(cmdLine.get(cmdLine.size() - 1), ".txt")) {
            throw new Error("Do not enter a filename!");
        }

        if (cmdLine.contains("-r")) {
            this.setCommandR(true);
        }
        if (cmdLine.contains("-i")) {
            this.setCommandI(true);
        }
        if (cmdLine.contains("-v")) {
            this.setCommandV(true);
        }

        this.wordOrRegex = cmdLine.get(cmdLine.size() - 2);
        this.pathName = cmdLine.get(cmdLine.size() - 1);
    }

    public static void main(String[] args) {
        Grep find = new Grep();
        find.correctCheck(args);
        try {
            for (String line : find.findContent()) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
