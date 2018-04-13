import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grep {

    // private boolean word = false;
    private boolean commandR = false;
    private boolean commandI = false;
    private boolean commandV = false;

    public void setCommandR(boolean commandR) {
        this.commandR = commandR;
    }

    public void setCommandI(boolean commandI) {
        this.commandI = commandI;
    }

    public void setCommandV(boolean commandV) {
        this.commandV = commandV;
    }

  /*  public void setWord(boolean word) {
        this.word = word;
    }*/

    public List<String> findContent(String wordOrRegex, String pathName) throws IOException {

        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<String> result = new ArrayList<>();
        String line = br.readLine();
        boolean status = false;

        while (line != null) {
            String toRegex = "";
            if (this.commandR) {
                toRegex = wordOrRegex;
            } else {
                for (int i = 0; i < wordOrRegex.length(); i++) {
                    toRegex += ("[" + wordOrRegex.charAt(i) + "]");
                }
                toRegex = ".*" + toRegex + ".*";
            }
            status = line.matches(toRegex);
            if (this.commandI && !status) {
                status = line.toLowerCase().matches(toRegex.toLowerCase());
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

    public static void main(String[] args) {
        Grep find = new Grep();
        List<String> cmdLine = Arrays.asList(args);
        if (cmdLine.size() < 2 || cmdLine.size() > 5)
            throw new Error("Input structure is incorrect!");
        if (cmdLine.contains("-r")) {
            find.setCommandR(true);
        }
        if (cmdLine.contains("-i")) {
            find.setCommandI(true);
        }
        if (cmdLine.contains("-v")) {
            find.setCommandV(true);
        }
        try {
            for (String line : find.findContent(cmdLine.get(cmdLine.size() - 2), cmdLine.get(cmdLine.size() - 1))) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
