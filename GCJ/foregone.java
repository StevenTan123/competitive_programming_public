import java.util.*;
import java.io.*;

public class foregone {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String n = in.readLine();
            int[] num1 = new int[n.length()];
            int[] num2 = new int[n.length()];
            for(int i = 0; i < n.length(); i++) {
                int digit = Character.getNumericValue(n.charAt(i));
                if(digit == 4) {
                    num1[i] = 2;
                    num2[i] = 2;
                }else {
                    num1[i] = digit;
                }
            }
            StringBuilder n1 = new StringBuilder();
            StringBuilder n2 = new StringBuilder();
            for(int i = 0; i < n.length(); i++) {
                if(num1[i] > 0 || (num1[i] == 0 && n1.length() > 0)) n1.append(num1[i]);
                if(num2[i] > 0 || (num2[i] == 0 && n2.length() > 0)) n2.append(num2[i]);
            }
            String res = "Case #" + t + ": " + n1 + " " + n2;
            out.println(res); 
        }
        in.close();
        out.close();
    }
}
