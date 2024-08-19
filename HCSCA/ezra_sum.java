import java.util.*;
import java.io.*;

public class ezra_sum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int res = -1;
        for (int i = 1; i < 10000000; i++) {
            int dig_sum = 0;
            int num = i;
            while (num > 0) {
                dig_sum += num % 10;
                num /= 10;
            }
            if (dig_sum == k) {
                n--;
            }
            if (n == 0) {
                res = i;
                break;
            }
        }
        out.println(res);

		in.close();
		out.close();
	}
}
