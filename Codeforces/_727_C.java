import java.util.*;
import java.io.*;

public class _727_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int odd = n % 2 == 0 ? n - 1 : n;
        int[] queried = new int[odd - 1];
        int dif = 0;
        for(int i = 0; i < odd - 1; i++) {
            queried[i] = query(i, i + 1, in);
            if(i % 2 == 0) {
                dif += queried[i];
            }else {
                dif -= queried[i];
            }
        }
        int sum = query(0, odd - 1, in);
        int[] ans = new int[n];
        ans[0] = (sum + dif) / 2;
        ans[odd - 1] = sum - ans[0];
        for(int i = 1; i < odd; i++) {
            ans[i] = queried[i - 1] - ans[i - 1];
        }
        if(odd < n) {
            ans[odd] = query(odd - 1, odd, in) - ans[odd - 1];
        }
        StringBuilder sb = new StringBuilder("! ");
        for(int i = 0; i < n; i++) {
            sb.append(ans[i]);
            if(i < n - 1) sb.append(' ');
        }
        System.out.println(sb.toString());
        in.close();
    }
    static int query(int i, int j, BufferedReader in) throws IOException {
        i++;
        j++;
        System.out.println("? " + i + " " + j);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }
}
