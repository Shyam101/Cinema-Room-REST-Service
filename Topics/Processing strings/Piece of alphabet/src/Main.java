import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        
        for(int i = 1; i < s.length(); i++) {
            if ( s.charAt(i) - s.charAt(i-1) != 1) {
                System.out.println("false");
                return;
            } 
        }
        
        System.out.println("true");
    }
}
