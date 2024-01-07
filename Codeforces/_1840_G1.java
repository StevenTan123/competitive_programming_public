import java.util.*;
import java.io.*;

public class _1840_G1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
        int ans = -1;
        int pos = 1;
        int[] seen = new int[1000005];
        seen[Integer.parseInt(in.readLine())] = pos;
        for (int i = 0; i < 2000; i++) {
            if (i < 1000) {
                System.out.println("+ 1");
                pos++;
            } else {
                System.out.println("+ 1000");
                pos += 1000;
            }
            System.out.flush();

            int val = Integer.parseInt(in.readLine());
            if (seen[val] > 0) {
                ans = pos - seen[val];
                break;
            } else {
                seen[val] = pos;
            }
        }
        System.out.println("! " + ans);

        in.close();
    }
}
