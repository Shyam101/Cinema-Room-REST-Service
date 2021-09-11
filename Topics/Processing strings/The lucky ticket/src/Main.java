import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        
        char[] s = sc.nextLine().toCharArray();
        
        int res = 0;
        for (int i = 0; i < 6; ++i) {
            if (i < 3) { 
                res += s[i]; 
            } else {
                res -= s[i];
            } 
        }
        
        System.out.println(res == 0 ? "Lucky" : "Regular");
        
        sc.close();        
    }
}
