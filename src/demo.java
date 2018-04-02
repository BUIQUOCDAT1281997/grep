public class demo {
    public static void main(String[] args) {
       //System.out.println(args);
       String a= "bui quoc dat";
       String regex = ".+[q][o][c].*";
       System.out.println(a.matches(regex));
       System.out.println(a.matches(".*(bui).*"));
       String b= "As a traditional family in Viet nam, I have a big one. My family has 5 members, including Mom, Dad, ";
       System.out.println(b.matches(".*(My family has).*"));

    }
}
