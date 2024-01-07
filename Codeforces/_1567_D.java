import java.util.*;
import java.io.*;

public class _1567_D {
    static int[] pow = new int[10];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        for(int i = 1; i < 10; i++) {
            pow[i] = pow[i - 1] * 10;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(line.nextToken());
            int n = Integer.parseInt(line.nextToken());
            int[] nums = new int[n];
            nums[0] = s - (n - 1);
            for(int i = 1; i < n; i++) {
                nums[i] = 1;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n - 1; i++) {
                int len = Integer.toString(nums[i]).length();
                int left = nums[i] - pow[len - 1];
                nums[i] -= left;
                nums[i + 1] += left;
                sb.append(nums[i]);
                sb.append(' ');
            }
            sb.append(nums[n - 1]);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
