import java.util.*;
import java.io.*;

public class _1853_D {
    static Random rand = new Random();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[][] a = new int[n][2];
            for (int i = 0; i < n; i++) {
                a[i][0] = Integer.parseInt(line.nextToken());
                a[i][1] = i;
            }
            shuffle(a);
            Arrays.sort(a, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            int pos = n;
            for (int i = 0; i < n; i++) {
                if (n - a[i][0] <= i) {
                    pos = i;
                    break;
                }
            }
            ArrayList<Integer> ordered = new ArrayList<Integer>();
            int l = 0;
            int r = n - 1;
            while (l < pos || r >= pos) {
                while (l < pos && a[l][0] == n - r - 1) {
                    ordered.add(l);
                    l++;
                }
                if (r >= pos) {
                    if (a[r][0] - (n - pos) != pos - l) {
                        break;
                    }
                    ordered.add(r);
                    r--;
                }
            }
            if (ordered.size() == n) {
                out.println("YES");
                int[] res = new int[n];
                for (int i = ordered.size() - 1; i >= 0; i--) {
                    int ind = ordered.get(i);
                    res[a[ind][1]] = n - i;
                    if (ind < pos) {
                        res[a[ind][1]] *= -1;
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    sb.append(res[i]);
                    sb.append(' ');
                }
                out.println(sb.toString());
            } else {
                out.println("NO");
            }
        }
		
        in.close();
		out.close();
	}
    static void shuffle(int[][] a) {
        for (int i = 1; i < a.length; i++) {
            int j = rand.nextInt(i);
            int[] temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
