import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Grep {

    // Вывод строк, содержащих указанное регулярное выражение или указанное слово
    public List<String> findContentFromPhrase(String phrase, String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            String[] str = line.split(phrase);
            if (str.length != 1) {
                result.add(line);
            } else if (!line.equals(str[0])) {
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
            String[] str = line.toLowerCase().split(word.toLowerCase());
            if (str.length != 1) {
                result.add(line);
            } else if (!line.toLowerCase().equals(str[0])) {
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // Вывод строк, не содержащих указанное слово
    public List<String> invertsCondition(String word, String pathName) throws IOException {
        List<String> gh = new Grep().findContentFromPhrase(word, pathName);
        File file = new File(pathName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> result = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            if (gh.indexOf(line) == -1) {
                result.add(line);
            }
            line = br.readLine();
        }
        return result;
    }

    // количество строк, содержащих указанное слово
    public int numberLines(String word, String pathName) throws IOException {
        return new Grep().findContentFromPhrase(word, pathName).size();
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
            String[] str = line.split(word);
            if (str.length != 1) {
                result += number + " ";
            } else if (!line.equals(str[0])) {
                result += number + " ";
            }
            number++;
            line = br.readLine();
        }
        if (result.equals("")) return "-1";
        return result.trim();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grep fileControl = new Grep();
        System.out.println("Enter the command : crep [-v][-i][-r] word inputname.txt\nEX : crep phone inputname.txt");
        String command = scanner.nextLine();
        String[] strs = command.split(" ");
        if (strs.length < 3 || !strs[0].equals("crep")) throw new Error("Input structure is incorrect!");
        if (strs.length == 3) {
            try {
                for (String line : fileControl.findContentFromPhrase(strs[1], strs[2])) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (strs[1].charAt(0) != '-') throw new Error("Input structure is incorrect!");
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

            }
        }
    }
}