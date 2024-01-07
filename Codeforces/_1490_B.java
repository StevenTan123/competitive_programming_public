import java.util.*;
import java.io.*;

public class _1490_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] freqs = new int[3];
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken()) % 3;
                freqs[a[j]]++;
            }
            int need = n / 3;
            int res = 0;
            for(int j = 0; j < 3; j++) {
                res += moves(j, freqs, need);
            }
            out.println(res);
		}
		in.close();
		out.close();
	}
    static int moves(int ind, int[] freqs, int need) {
        if(freqs[ind] >= need) return 0;
        int curneed = need - freqs[ind];
        ind--;
        if(ind < 0) ind += 3;
        int counter = 1;
        int ret = 0;
        while(curneed > 0) {
            if(freqs[ind] > need) {
                int have = freqs[ind] - need;
                if(have >= curneed) {
                    ret += curneed * counter;
                    break;
                }else {
                    ret += have * counter;
                    curneed -= have;
                }
            }
            ind--;
            if(ind < 0) ind += 3;
            counter++;
        }
        return ret;
    }
}
