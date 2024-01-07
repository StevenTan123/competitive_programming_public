import java.util.*;
import java.io.*;

public class _1490_G {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            long max = 0;
            long[] psums = new long[n];
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken());
                psums[j] = (j > 0 ? psums[j - 1] : 0) + a[j];
                max = Math.max(max, psums[j]);
            }
            line = new StringTokenizer(in.readLine());
            long[] x = new long[m];
            long[][] leftovers = new long[m][4];
            for(int j = 0; j < m; j++) {
                x[j] = Integer.parseInt(line.nextToken());
                long sub = x[j] - max;
                long need = 0;
                if(sub > 0 && psums[n - 1] > 0) {
                    need = sub / psums[n - 1];
                    if(sub % psums[n - 1] != 0) need++;
                }
                if(need < 0) need = 0;
                leftovers[j][0] = x[j] - psums[n - 1] * need;
                leftovers[j][1] = j;
                leftovers[j][2] = need;
            }
            Arrays.sort(leftovers, new Comparator<long[]>() {
                @Override
                public int compare(long[] o1, long[] o2) {
                    if(o1[0] > o2[0]) return 1;
                    else if(o2[0] > o1[0]) return -1;
                    return 0;
                }
            });
            int pointer = 0;
            long maxsofar = psums[pointer];
            for(int j = 0; j < m; j++) {
                while(maxsofar < leftovers[j][0]) {
                    pointer++;
                    if(pointer >= n) break;
                    maxsofar = Math.max(maxsofar, psums[pointer]);
                }
                if(pointer >= n) leftovers[j][3] = -1;
                else leftovers[j][3] = pointer + leftovers[j][2] * n;
            }
            Arrays.sort(leftovers, new Comparator<long[]>() {
                @Override
                public int compare(long[] o1, long[] o2) {
                    if(o1[1] > o2[1]) return 1;
                    else if(o2[1] > o1[1]) return -1;
                    return 0;
                }
            });
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < m; j++) {
                sb.append(leftovers[j][3]);
                sb.append(' ');
            }
            out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
