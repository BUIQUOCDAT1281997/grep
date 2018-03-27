
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class GrepTest {

    Grep grep = new Grep();

    @org.junit.jupiter.api.Test
    void findContentFromPhrase() {
        String str = "";
        try {
            for (String element : grep.findContentFromPhrase("Viet nam", "inputname.txt")) {
                str += element + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(str, "As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, \n" +
                "is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we \n");
    }

    @org.junit.jupiter.api.Test
    void findContentIgnoreCase() {
        String str = "";
        try {
            for (String element : grep.findContentIgnoreCase("my", "inputname.txt")) {
                str += element + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(str, "As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, \n" +
                "Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a \n" +
                "teacher. My Dad name is Trung. He is tall and very strong. His job is doctor. For me, my mom is \n" +
                "the most beautiful woman, and my dad is the most wonderful man. And my grandma name is \n" +
                "my sister. Her name is Linh. She is 26 years old, and she is a beautiful woman like Mom. Now, she \n" +
                "is living in Ha Noi capital Viet nam, because of her jobs. I really love my family, and I hope that we \n");
    }

    @org.junit.jupiter.api.Test
    void invertsCondition() {
        String str = "";
        try {
            for (String element : grep.invertsCondition("my", "inputname.txt")) {
                str += element + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(str, "As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, \n" +
                "Grandma, sister, and me. My mom name is Giang. She has long hair and black eyes. She is a \n" +
                "Tam. She is 95 years old, and next 5 years, we will organize the 100th longevity wishing ceremony, \n" +
                "and I look forward to taking part in this ceremony. Besides, another woman who I love so much is \n" +
                "are always together anyway.\n");
    }

    @org.junit.jupiter.api.Test
    void numberLines() {
        try {
            assertEquals(7, grep.numberLines("is", "inputname.txt"));
            assertEquals(4, grep.numberLines("name", "inputname.txt"));
            assertEquals(2, grep.numberLines("beautiful", "inputname.txt"));
            assertEquals(0, grep.numberLines("QuocDat", "inputname.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void countMatches() {

        try {
            assertEquals(14, grep.countMatches("is", "inputname.txt"));
            assertEquals(4, grep.countMatches("name", "inputname.txt"));
            assertEquals(0, grep.countMatches("QuocDat", "inputname.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void positionLines() {
        try {
            assertEquals("4 7", grep.positionLines("beautiful", "inputname.txt"));
            assertEquals("2 3 4 5 6 7 8", grep.positionLines("is", "inputname.txt"));
            assertEquals("-1", grep.positionLines("QuocDat", "inputname.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}