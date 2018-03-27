public class demo {
    public static void main(String[] args) {
        String s= " Toi KHOng tin TOi, cho chet";
        String[] a =s.toLowerCase().split("TOI".toLowerCase());
        //System.out.println();
        for (String element : a){
            System.out.println(element);
        }
        System.out.println("".length());
    }
}
