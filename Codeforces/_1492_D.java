import java.util.*;
import java.io.*;

public class _1492_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        if(a + b > 1 && k > a + b - 2) {
            out.println("NO");
        }else if(b == a + b || b == 1) {
            if(k > 0) {
                out.println("NO");
            }else {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < a + b; i++) {
                    if(i < b) {
                        sb.append(1);
                    }else {
                        sb.append(0);
                    }
                }
                out.println("YES");
                out.println(sb.toString());
                out.println(sb.toString());
            }
        }else {
            int[] x = new int[a + b];
            int[] y = new int[a + b];
            x[0] = 1;
            y[0] = 1;
            x[1] = 1;
            y[1 + k] = 1;
            int left = b - 2;
            for(int i = 2; i < a + b; i++) {
                if(i == 1 + k) continue;
                if(left > 0) {
                    x[i] = 1;
                    y[i] = 1;
                    left--;
                }
            }
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for(int i = 0; i < a + b; i++) {
                sb1.append(x[i]);
                sb2.append(y[i]);
            }
            out.println("YES");
            out.println(sb1.toString());
            out.println(sb2.toString());
        }
		in.close();
		out.close();
	}
}
