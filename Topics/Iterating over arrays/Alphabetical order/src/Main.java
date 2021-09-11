import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String[] st = s.split(" ");

        for (int i = 1; i < st.length; i++) {
            if (st[i-1].compareTo(st[i]) > 0) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }
}