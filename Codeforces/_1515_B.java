import java.util.*;
import java.io.*;

public class _1515_B {
    public static void main(String[] args) throws IOException {
        HashSet<Integer> squares = new HashSet<Integer>();
        for(int i = 0; i < 35000; i++) {
            squares.add(i * i);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            boolean possible = false;
            if(n % 4 == 0) {
                if(squares.contains(n / 4)) possible = true;
            }
            if(n % 2 == 0) {
                if(squares.contains(n / 2)) possible = true;
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
