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

            Grep grep1 = new Grep("Viet", "inputname.txt");
            List<String> word = new ArrayList<String>();
            word.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            word.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            assertEquals(grep1.findContent(), word);


            //Вывод строк, содержащих указанное слово, игнорировать регистр слов

            Grep grep2 = new Grep("GIANG", "inputname.txt");
            grep2.setCommandI(true);
            List<String> ignoreCase = new ArrayList<String>();
            ignoreCase.add("Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a ");
            assertEquals(grep2.findContent(), ignoreCase);


            //Вывод строк, содержащих указанное регулярное выражение

            Grep grep3 = new Grep(".*(She is).[0-9][0-9].*", "inputname.txt");
            grep3.setCommandR(true);
            List<String> regex = new ArrayList<String>();
            regex.add("Tam. She is 95 years old, and next 5 years, we will organize the 100th longevity wishing ceremony, ");
            regex.add("my sister. Her name is Linh. She is 26 years old, and she is a beautiful woman like Mom. Now, she ");
            assertEquals(grep3.findContent(), regex);


            // Вывод строк, не содержащих указанное слово

            Grep grep4 = new Grep("is", "inputname.txt");
            grep4.setCommandV(true);
            List<String> invertsCondition = new ArrayList<String>();
            invertsCondition.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            invertsCondition.add("are always together anyway.");
            assertEquals(grep4.findContent(), invertsCondition);


            //Вывод строк при использовании много флагов

            Grep grep5 = new Grep(".*(SHE|HE).(is).*", "inputname.txt");
            grep5.setCommandI(true);
            grep5.setCommandV(true);
            grep5.setCommandR(true);
            List<String> list = new ArrayList<String>();
            list.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            list.add("the most beautiful woman, and my dad is the most wonderful man. And my grandma name is ");
            list.add("and I look forward to taking part in this ceremony. Besides, another woman who I love so much is ");
            list.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            list.add("are always together anyway.");
            assertEquals(list, grep5.findContent());


            Grep grep6 = new Grep("sHE", "inputname.txt");
            grep6.setCommandI(true);
            grep6.setCommandV(true);
            List<String> listTest6 = new ArrayList<String>();
            listTest6.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            listTest6.add("teacher. My Dad name is Trung. He is tall and very strong. His job is doctor. For me, my mom is ");
            listTest6.add("the most beautiful woman, and my dad is the most wonderful man. And my grandma name is ");
            listTest6.add("and I look forward to taking part in this ceremony. Besides, another woman who I love so much is ");
            listTest6.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            listTest6.add("are always together anyway.");
            assertEquals(grep6.findContent(), listTest6);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}