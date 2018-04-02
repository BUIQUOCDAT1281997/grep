
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrepTest {

    @org.junit.jupiter.api.Test
    void findContent() {
        Grep grep = new Grep();
        try {
            // Вывод строк, содержащих указанное слово
            List<String> word = new ArrayList<>();
            word.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            word.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            assertEquals(grep.findContent("Viet", "inputname.txt", "word"), word);

            //Вывод строк, содержащих указанное слово, игнорировать регистр слов
            List<String> ignoreCase = new ArrayList<>();
            ignoreCase.add("Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a ");
            assertEquals(grep.findContent("GIANG", "inputname.txt", "-i"), ignoreCase);

            //Вывод строк, содержащих указанное регулярное выражение
            List<String> regex = new ArrayList<>();
            regex.add("Tam. She is 95 years old, and next 5 years, we will organize the 100th longevity wishing ceremony, ");
            regex.add("my sister. Her name is Linh. She is 26 years old, and she is a beautiful woman like Mom. Now, she ");
            assertEquals(grep.findContent(".*(She is).[0-9][0-9].*", "inputname.txt", "-r"), regex);

            // Вывод строк, не содержащих указанное слово
            List<String> invertsCondition = new ArrayList<>();
            invertsCondition.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            invertsCondition.add("are always together anyway.");
            assertEquals(grep.findContent("is", "inputname.txt", "-v"), invertsCondition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}