import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            int j = 0;
            int k = i;
            while (j < b.length() && k < a.length() && a.charAt(k) == b.charAt(j)) {
                j++;
                k++;
            }

            if (j == b.length()) {
                res++;
                i = k - 1;
            }
        }

        System.out.println(res);
    }
}
