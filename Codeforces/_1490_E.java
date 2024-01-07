import java.util.*;
import java.io.*;

public class _1490_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            int[][] a = new int[n][3];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                a[j][0] = Integer.parseInt(line.nextToken());
                a[j][1] = j;
            }
            Arrays.sort(a, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            long[] psums = new long[n];
            long cursum = 0;
            for(int j = 0; j < n; j++) {
                cursum += a[j][0];
                psums[j] = cursum;
            }
            int count = 1;
            a[n - 1][2] = 1;
            for(int j = n - 2; j >= 0; j--) {
                if(psums[j] >= a[j + 1][0]) {
                    a[j][2] = 1;
                    count++;
                }else {
                    break;
                }
            }
            Arrays.sort(a, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            out.println(count);
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(a[j][2] == 1) {
                    sb.append(j + 1);
                    sb.append(' ');
                }
            }
            out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
