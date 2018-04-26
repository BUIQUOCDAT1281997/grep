import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrepTest {

    @org.junit.jupiter.api.Test
    void findContent() {
        try {


            // Вывод строк, содержащих указанное слово

            Grep grep1 = new Grep();
            String[] test1 = new String[]{"Viet", "inputname.txt"};
            grep1.correctCheck(test1);
            List<String> word = new ArrayList<String>();
            word.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            word.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            assertEquals(grep1.findContent(), word);


            //Вывод строк, содержащих указанное слово, игнорировать регистр слов

            Grep grep2 = new Grep();
            String[] test2 = new String[]{"-i", "GIANG", "inputname.txt"};
            grep2.correctCheck(test2);
            List<String> ignoreCase = new ArrayList<String>();
            ignoreCase.add("Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a ");
            assertEquals(grep2.findContent(), ignoreCase);


            //Вывод строк, содержащих указанное регулярное выражение

            Grep grep3 = new Grep();
            String[] test3 = new String[]{"-r", ".*(She is).[0-9][0-9].*", "inputname.txt"};
            grep3.correctCheck(test3);
            List<String> regex = new ArrayList<String>();
            regex.add("Tam. She is 95 years old, and next 5 years, we will organize the 100th longevity wishing ceremony, ");
            regex.add("my sister. Her name is Linh. She is 26 years old, and she is a beautiful woman like Mom. Now, she ");
            assertEquals(grep3.findContent(), regex);


            // Вывод строк, не содержащих указанное слово

            Grep grep4 = new Grep();
            String[] test4 = new String[]{"-v", "is", "inputname.txt"};
            grep4.correctCheck(test4);
            List<String> invertsCondition = new ArrayList<String>();
            invertsCondition.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            invertsCondition.add("are always together anyway.");
            assertEquals(grep4.findContent(), invertsCondition);


            //Вывод строк при использовании много флагов

            Grep grep5 = new Grep();
            String[] test5 = new String[]{"-i", "-v", "-r", ".*(SHE|HE).(is).*", "inputname.txt"};
            grep5.correctCheck(test5);
            List<String> list = new ArrayList<String>();
            list.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            list.add("the most beautiful woman, and my dad is the most wonderful man. And my grandma name is ");
            list.add("and I look forward to taking part in this ceremony. Besides, another woman who I love so much is ");
            list.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            list.add("are always together anyway.");
            assertEquals(list, grep5.findContent());


            Grep grep6 = new Grep();
            String[] test6 = new String[]{"-i","-v","sHE","inputname.txt"};
            grep6.correctCheck(test6);
            List<String> listTest6 = new ArrayList<String>();
            listTest6.add("As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ");
            listTest6.add("teacher. My Dad name is Trung. He is tall and very strong. His job is doctor. For me, my mom is ");
            listTest6.add("the most beautiful woman, and my dad is the most wonderful man. And my grandma name is ");
            listTest6.add("and I look forward to taking part in this ceremony. Besides, another woman who I love so much is ");
            listTest6.add("is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we ");
            listTest6.add("are always together anyway.");
            assertEquals(grep6.findContent(),listTest6);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}