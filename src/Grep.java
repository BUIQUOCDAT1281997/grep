import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grep {

    public List<String> findContent(String wordOrRegex, String pathName, String gr) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            switch (gr) {
                case "word": {
                    if (line.matches(".*(" + wordOrRegex + ").*")) {
                        result.add(line);
                    }
                    break;
                }
                case "-r": {
                    if (line.matches(wordOrRegex)) {
                        result.add(line);
                    }
                    break;
                }
                case "-v": {
                    if (!line.matches(".*(" + wordOrRegex + ").*")) {
                        result.add(line);
                    }
                    break;
                }
                case "-i": {
                    if (line.toLowerCase().matches(".*(" + wordOrRegex.toLowerCase() + ").*")) {
                        result.add(line);
                    }
                    break;
                }
            }
            line = br.readLine();
        }
        return result;
    }

    public static void main(String[] args) {
        Grep fileControl = new Grep();
        if (args.length < 3 || args.length > 4 || !args[0].equals("crep"))
            throw new Error("Input structure is incorrect!");
        if (args.length == 3) {
            try {
                for (String line : fileControl.findContent(args[1], args[2], "word")) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (args[1].charAt(0) != '-') throw new Error("Input structure is incorrect!");
            try {
                for (String line : fileControl.findContent(args[2], args[3], args[1])) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
