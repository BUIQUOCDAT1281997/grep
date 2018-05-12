import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private boolean commandR = false;
    private boolean commandI = false;
    private boolean commandV = false;

    private String wordOrRegex, pathName;

    public Grep(String word, String filename){
        this.wordOrRegex=word;
        this.pathName= filename;
    }

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
        boolean status;

        while (line != null) {
            String toRegex;
            if (this.commandR) {
                toRegex = wordOrRegex;
            } else {
                toRegex = ".*" + Pattern.quote(wordOrRegex) + ".*";
            }
            Pattern p;
            if (this.commandI){
                p = Pattern.compile(toRegex,Pattern.CASE_INSENSITIVE);
            }else {
                p= Pattern.compile(toRegex);
            }
            Matcher m = p.matcher(line);
            status=m.matches();
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
}
