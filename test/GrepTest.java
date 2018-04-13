import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrepTest {

    @Test
    void findContent() {
        try {
            // Вывод строк, содержащих указанное слово
            Grep grep1 = new Grep();
            List<String> word = new ArrayList<>();
            word.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            word.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            assertEquals(grep1.findContent("Viet", "inputname.txt"), word);

            //Вывод строк, содержащих указанное слово, игнорировать регистр слов
            Grep grep2 = new Grep();
            grep2.setCommandI(true);
            List<String> ignoreCase = new ArrayList<>();
            ignoreCase.add("Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a ");
            assertEquals(grep2.findContent("GIANG", "inputname.txt"), ignoreCase);

            //Вывод строк, содержащих указанное регулярное выражение
            Grep grep3 = new Grep();
            grep3.setCommandR(true);
            List<String> regex = new ArrayList<>();
            regex.add("Tam. She is 95 years old, and next 5 years, we will organize the 100th longevity wishing ceremony, ");
            regex.add("my sister. Her name is Linh. She is 26 years old, and she is a beautiful woman like Mom. Now, she ");
            assertEquals(grep3.findContent(".*(She is).[0-9][0-9].*", "inputname.txt"), regex);

            // Вывод строк, не содержащих указанное слово
            Grep grep4 = new Grep();
            grep4.setCommandV(true);
            List<String> invertsCondition = new ArrayList<>();
            invertsCondition.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            invertsCondition.add("are always together anyway.");
            assertEquals(grep4.findContent("is", "inputname.txt"), invertsCondition);

            //Вывод строк при использовании много флагов
            Grep grep5 = new Grep();
            grep5.setCommandV(true);
            grep5.setCommandR(true);
            grep5.setCommandI(true);
            List<String> list = new ArrayList<>();
            list.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            list.add("the most beautiful woman, and my dad is the most wonderful man. And my grandma name is ");
            list.add("and I look forward to taking part in this ceremony. Besides, another woman who I love so much is ");
            list.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            list.add("are always together anyway.");
            assertEquals(list, grep5.findContent(".*(SHE|HE).(is).*", "inputname.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}