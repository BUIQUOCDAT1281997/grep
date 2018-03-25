import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Grep {

    // Вывод строк, содержащих указанное слово
    public List<String> findContentFromWord(String word, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            List<String> listWords = Arrays.asList(line.split(" "));
            if (listWords.indexOf(word) != -1) {
                //System.out.println(line);
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // Вывод строк, содержащих указанное регулярное выражение
    public List<String> findContentFromPhrase(String phrase, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            String[] str = line.split(phrase);
            if (str.length != 1) {
                //System.out.println(line);
                result.add(line);
            } else if (!line.equals(str[0])) {
                //System.out.println(line);
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // Вывод строк, содержащих указанное слово, игнорировать регистр слов
    public List<String> findContentIgnoreCase(String word, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            List<String> listWords = Arrays.asList(line.toLowerCase().split(" "));
            if (listWords.indexOf(word.toLowerCase()) != -1) {
                //System.out.println(line);
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // Вывод строк, не содержащих указанное слово
    public List<String> invertsCondition(String word, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            List<String> listWord = Arrays.asList(line.split(" "));
            if (listWord.indexOf(word) == -1) {
                //System.out.println(line);
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // количество строк, содержащих указанное слово
    public int numberLines(String word, String pathName) throws IOException {
        return new Grep().findContentFromWord(word, pathName).size();
    }

    // Вычисление количества раз слово присутствует в тесте.
    public int countMatches(String word, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int count = 0;
        while (line != null) {
            List<String> listWords = Arrays.asList(line.split(" "));
            for (String element : listWords) {
                if (element.equals(word)) {
                    count++;
                }
            }
            line = br.readLine();
        }
        return count;
    }

    // Вывод положениий строк, содержащих указанное слово
    public String positionLines(String word, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int number = 1;
        String result = "";
        while (line != null) {
            List<String> listWords = Arrays.asList(line.split(" "));
            if (listWords.indexOf(word) != -1) {
                result += (number + " ");
            }
            line = br.readLine();
            number++;
        }
        if (result.equals("")) return "-1";
        return result.trim();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grep fileControl = new Grep();
        try {
            fileControl.positionLines("beautiful", "inputname.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter the command : crep [-v][-i][-r] word inputname.txt\nEX : crep phone inputname.txt");
        String command = scanner.nextLine();
        String[] strs = command.split(" ");
        switch (strs[1]) {
            case "-i": {
                try {
                    for (String line : fileControl.findContentIgnoreCase(strs[2], strs[3])) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "-v": {
                try {
                    for (String line : fileControl.invertsCondition(strs[2], strs[3])) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "-r": {
                String pathName = strs[strs.length - 1];
                String phrase = command.substring(command.indexOf('r', 2) + 2, command.length() - pathName.length() - 1);
                try {
                    for (String line : fileControl.findContentFromPhrase(phrase, pathName)) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                try {
                    for (String line : fileControl.findContentFromWord(strs[1], strs[2])) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Vietnam,
//[-v][-i][-r]